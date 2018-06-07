package ua.lemekh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.lemekh.Util.StringUtil;
import ua.lemekh.mailEvent.OnRegistrationCompleteEvent;
import ua.lemekh.model.RoleEnum;
import ua.lemekh.model.User;
import ua.lemekh.model.VerificationToken;
import ua.lemekh.service.GroupService;
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
    public static final String ADMIN = "adminadmin";
    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private GroupService groupService;

    @Autowired
    GroupService categoryService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") final User userForm, BindingResult bindingResult, Model model, final HttpServletRequest request) {
        model.addAttribute("show", categoryService.list());
        if (StringUtils.isEmpty(userForm.getPassword())){
            userForm.setPassword(ADMIN);
            userForm.setConfirmPassword(ADMIN);
        }
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
        model.addAttribute("show", categoryService.list());
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("groups", groupService.list());
        return "registration";
    }

    @RequestMapping(value = "/registerLecturer/{id}", method = RequestMethod.GET)
    public String registration(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("userForm", userService.getUserById(id));
        return "registerlecturer";
    }
    @RequestMapping(value = "/registerLecturer", method = RequestMethod.POST)
    public String registrationLectur(@ModelAttribute("userForm") final User user) {
        userService.updateUserPassword(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration
            (Model model, @RequestParam("token") String token) {
        model.addAttribute("show", categoryService.list());
        return userService.confirmVerification(token);
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
