package ua.lemekh.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.lemekh.dao.RoleDao;
import ua.lemekh.dto.NewPublicationDTO;
import ua.lemekh.model.Group;
import ua.lemekh.model.Publication;
import ua.lemekh.model.User;
import ua.lemekh.service.GroupService;
import ua.lemekh.service.PublicationService;
import ua.lemekh.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * Created by Ostap on 20.06.2017.
 */
@Controller
@PreAuthorize("hasAnyRole('ROLE_LECTURER','ROLE_ADMIN')")
public class AdminController {

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    private Path path;

    @RequestMapping(value = "editusers", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("show", groupService.list());
        return "listUsers";
    }

    @RequestMapping(value = "editusers/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "OK";
    }

    @RequestMapping(value = "editusers/update/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        User userUpdate = userService.getUserById(id);
        if (user.isEnabled()) {
            userUpdate.setEnabled(false);
        } else {
            userUpdate.setEnabled(true);
        }
        return userService.updateUser(userUpdate);

    }

    @RequestMapping(value = "createlot", method = RequestMethod.GET)
    public String createPublication(Model model) {
        model.addAttribute("groups", groupService.getCategories());
        model.addAttribute("publication", new NewPublicationDTO());
        return "createlot";
    }

    @RequestMapping(value = "createlot", method = RequestMethod.POST)
    public String createPublication(@Valid @ModelAttribute("lot") NewPublicationDTO newPublicationDTO, BindingResult bindingResult, HttpServletRequest request) {
        publicationService.createPublication(request, newPublicationDTO);
        return "redirect:/";
    }

    @RequestMapping(value = "/updateImage", method = RequestMethod.POST, produces = {"application/json"})
    public @ResponseBody
    HashMap<String, Object> echoFile(@RequestParam("img") MultipartFile multipartFile) throws Exception {

        Long size = multipartFile.getSize();
        String contentType = multipartFile.getContentType();
        InputStream stream = multipartFile.getInputStream();
        byte[] bytes = IOUtils.toByteArray(stream);

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("fileoriginalsize", size);
        map.put("contenttype", contentType);
        map.put("base64", new String(Base64Utils.encode(bytes)));

        return map;
    }

    @RequestMapping(value = "/editcategory", method = RequestMethod.GET)
    public String editCategory(Model model) {
        model.addAttribute("groups", groupService.list());
        return "editcategory";
    }

    @RequestMapping(value = "/editcategory", method = RequestMethod.POST)
    @ResponseBody
    public String createCategory(@Valid @RequestBody Group group) {
        groupService.createCategory(group);
        return "Group successful created";
    }

    @RequestMapping(value = "/editcategory/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteCategory(@PathVariable("id") Integer id) {
        groupService.deleteCategory(id);
        return "Group successful deleted";
    }

    @RequestMapping(value = "editlot/{id}", method = RequestMethod.GET)
    public String editLot(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("show", groupService.list());
        model.addAttribute("lot", publicationService.getProductById(id));
        model.addAttribute("categories", groupService.getCategories());
        return "editlot";
    }

    @RequestMapping(value = "editlot/{id}", method = RequestMethod.POST)
    public String editLot(@Valid @ModelAttribute("lot") Publication products, HttpServletRequest request) {
        publicationService.updateproduct(products);
        uploadImage(request, products);
        return "`";
    }

    @RequestMapping(value = "remove/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        publicationService.deleteProduct(id);
        return "redirect:/";
    }

    private void uploadImage(HttpServletRequest request, Publication products) {
        /*MultipartFile productsImage = products.getImage();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/resources/img/");
        path = Paths.get(rootDirectory +
                +products.getId() + ".png");
        if (productsImage != null && !productsImage.isEmpty()) {
            try {
                productsImage.transferTo(new File(path.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

}
