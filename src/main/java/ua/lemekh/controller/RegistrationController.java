package ua.lemekh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.lemekh.mailEvent.OnRegistrationCompleteEvent;
import ua.lemekh.model.Search;
import ua.lemekh.model.User;
import ua.lemekh.model.VerificationToken;
import ua.lemekh.service.UserService;
import ua.lemekh.service.VerificationTokenService;
import ua.lemekh.validation.UserValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

/**
 * Created by Ostap on 13.06.2017.
 */
@Controller
public class RegistrationController {
    @Autowired
    UserService userService;
    @Autowired
    Search search;

    @Autowired
    UserValidator userValidator;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    VerificationTokenService verificationTokenService;


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") final User userForm, BindingResult bindingResult, Model model, final HttpServletRequest request) {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userForm);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(userForm, request.getLocale(), getAppUrl(request)));

            }
        });
        thread.start();
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error) {
        model.addAttribute("search", search);
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }
        return "login";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("search", search);
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration
            ( Model model, @RequestParam("token") String token) {
        model.addAttribute("search", search);
        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
        if (verificationToken == null) {
            return "redirect:/" ;
        }

        User user = verificationToken.getUser();

        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return "redirect:/" ;
        }
        user.setEnabled(true);
        userService.updateUser(user);
        return "redirect:/login";
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
