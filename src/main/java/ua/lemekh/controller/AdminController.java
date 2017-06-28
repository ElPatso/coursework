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
import ua.lemekh.model.Category;
import ua.lemekh.model.Products;
import ua.lemekh.model.User;
import ua.lemekh.service.CategoryService;
import ua.lemekh.service.ProductService;
import ua.lemekh.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    private Path path;

    @RequestMapping(value = "editusers", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("users",userService.getUsers());
        model.addAttribute("show", categoryService.list());
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
    public String createLot(Model model) {
        model.addAttribute("lot", new Products());
        model.addAttribute("show", categoryService.list());
        model.addAttribute("categories", categoryService.getCategories());
        return "createlot";
    }

    @RequestMapping(value = "createlot", method = RequestMethod.POST)
    public String createLot(@Valid @ModelAttribute("lot") Products products, BindingResult bindingResult, HttpServletRequest request ) {
        if (bindingResult.hasErrors()){
            return "redirect:/createlot";
        }
        productService.addProduct(products);
        uploadImage(request, products);
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
        model.addAttribute("categories", categoryService.list());
        model.addAttribute("show", categoryService.list());
        return "editcategory";
    }

    @RequestMapping(value = "/editcategory", method = RequestMethod.POST)
    @ResponseBody
    public String createCategory(@Valid @RequestBody Category category) {
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        if (category.getId() == 0){
        categoryService.createCategory(newCategory);
        }
        else {
            newCategory.setParentCategory(categoryService.getCategoryById(category.getId()));
            categoryService.createCategory(newCategory);
        }
        return "Category successful created";
    }

    @RequestMapping(value = "/editcategory/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteCategory(@PathVariable("id") Integer id) {
        categoryService.deleteCategory(id);
        return "Category successful deleted";
    }

    @RequestMapping(value = "editlot/{id}", method = RequestMethod.GET)
    public String editLot(Model model, @PathVariable("id") Integer id){
        model.addAttribute("show", categoryService.list());
        model.addAttribute("lot", productService.getProductById(id));
        model.addAttribute("categories", categoryService.getCategories());
        return "editlot";
    }

    @RequestMapping(value = "editlot/{id}", method = RequestMethod.POST)
    public String editLot(@Valid @ModelAttribute("lot") Products products, HttpServletRequest request) {
        productService.updateproduct(products);
        uploadImage(request,products);
        return "redirect:/lot/{id}";
    }

    @RequestMapping(value = "remove/{id}")
    public String deleteProduct(@PathVariable("id") Integer id){
        productService.deleteProduct(id);
        return "redirect:/";
    }

    private void uploadImage(HttpServletRequest request, Products products){
        MultipartFile productsImage = products.getImage();
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
    }

}
