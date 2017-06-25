package ua.lemekh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.lemekh.model.*;
import ua.lemekh.service.CartService;
import ua.lemekh.service.CategoryService;
import ua.lemekh.service.CommentService;
import ua.lemekh.service.ProductService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Ostap on 16.06.2017.
 */
@Controller
public class ProductController {
    @Autowired
    Search search;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String main(Model model, Integer offset, Integer maxResults){
        model.addAttribute("products", productService.getProductList(offset, maxResults));
        model.addAttribute("count", productService.count());
        model.addAttribute("offset", offset);
        model.addAttribute("search", search);
        model.addAttribute("show", categoryService.list());
        return "home";
    }

    @RequestMapping(value = "/lot/{id}", method = RequestMethod.GET)
    public String lot(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("execute", cartService.isExecute(id));
        model.addAttribute("search", search);
        model.addAttribute("show", categoryService.list());
        return "lot";
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(value="/lot/{id}/addComment", method=RequestMethod.POST)
    @ResponseBody
    public Comments createComment(@PathVariable Integer id, @Valid @RequestBody Comments comments) {
        comments.setProducts(productService.getProductById(id));
        return commentService.addComment(comments);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "cart", method = RequestMethod.GET)
    public String cart(Model model){
        model.addAttribute("search", search);
        model.addAttribute("cart", cartService.getCartList());
        model.addAttribute("show", categoryService.list());
        return "cart";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/lot/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String addtoCart(@PathVariable Integer id){

        cartService.addToCart(id);
        return "OK";
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "cart/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Products deleteFromCart(@PathVariable Integer id) {

        return cartService.deleteFromcart(id);
    }


    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String searchPost(Model model, @ModelAttribute("search") Search search) {

        model.addAttribute("show", categoryService.list());
        model.addAttribute("search", search);
        model.addAttribute("products", productService.getProductListBySearch(search.getSearchRow()));
        return "search";
    }


    @RequestMapping(value = "category", method = RequestMethod.GET)
    public String categoryTest(@RequestParam("name") String name, Model model, Integer offset, Integer maxResults) {
        model.addAttribute("products", productService.getProductsByCategory(name,offset, maxResults));
        model.addAttribute("count", productService.CountForCategory(name));
        model.addAttribute("offset", offset);
        model.addAttribute("search", search);
        model.addAttribute("show", categoryService.list());
        return "category";
    }
}
