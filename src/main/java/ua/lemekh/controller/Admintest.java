package ua.lemekh.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import ua.lemekh.model.Products;
import ua.lemekh.model.Search;
import ua.lemekh.model.User;
import ua.lemekh.service.CategoryService;
import ua.lemekh.service.ProductService;
import ua.lemekh.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by Ostap on 20.06.2017.
 */
@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class Admintest {
    @Autowired
    private Search search;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    private Path path;

    @RequestMapping(value = "editusers", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("search", search);
        model.addAttribute("users",userService.getUsers());
        model.addAttribute("show", categoryService.list());
        return "listUsers";
    }

    @RequestMapping(value = "editusers/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUser(@PathVariable Integer id) {
        System.out.println("dosmth");
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
    public String createLot(Model model) {
        model.addAttribute("search", search);
        model.addAttribute("lot", new Products());
        model.addAttribute("show", categoryService.list());
        return "createlot";
    }

    @RequestMapping(value = "createlot", method = RequestMethod.POST)
    public String createLot(@ModelAttribute("lot") Products products, HttpServletRequest request) {
        productService.addProduct(products);
        MultipartFile productsImage = products.getImage();
        System.out.println("++++++++" + productsImage.getName());
        String rootDirectory = request.getSession().getServletContext().getRealPath("/resources/img/");
        path = Paths.get(rootDirectory +
                +products.getId() + ".png");
        if (productsImage != null && !productsImage.isEmpty()) {
            try {
                productsImage.transferTo(new File(path.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    @RequestMapping(value = "/editcategory" , method = RequestMethod.GET)
    public String editCategory(Model model){
        model.addAttribute("search", search);
        model.addAttribute("categories", categoryService.list());
        model.addAttribute("show", categoryService.list());
        return "editcategory";
    }
}
