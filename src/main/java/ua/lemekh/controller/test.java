package ua.lemekh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.lemekh.model.Comments;
import ua.lemekh.model.Products;
import ua.lemekh.model.Search;
import ua.lemekh.model.User;
import ua.lemekh.service.CartService;
import ua.lemekh.service.ProductService;

import java.util.List;

/**
 * Created by Ostap on 16.06.2017.
 */
@Controller
public class test {
    @Autowired
    Search search;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String main(Model model, Integer offset, Integer maxResults){
        model.addAttribute("products", productService.getProductList(offset, maxResults));
        model.addAttribute("count", productService.count());
        model.addAttribute("offset", offset);
        model.addAttribute("userForm", new User());
        model.addAttribute("search", search);

        return "home";
    }

    @RequestMapping(value = "/lot/{id}", method = RequestMethod.GET)
    public String lot(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("execute", cartService.isExecute(id));
        model.addAttribute("search", search);
        return "lot";
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(value="/lot/{id}/addComment", method=RequestMethod.POST)
    @ResponseBody
    public Comments createSmartphone(@PathVariable Integer id, @RequestBody Comments comments) {

        comments.setProducts(productService.getProductById(id));
        return productService.addComment(comments);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "cart", method = RequestMethod.GET)
    public String cart(Model model){
        model.addAttribute("search", search);
        model.addAttribute("cart", cartService.getCartList());
        return "cart";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/lot/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String addtoCart(@PathVariable Integer id){
        System.out.println("dosmth");
        cartService.addToCart(id);
        return "OK";
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "cart/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Products deleteFromCart(@PathVariable Integer id) {
        System.out.println("dosmth");
        return cartService.deleteFromcart(id);
    }


    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String searchPost(Model model, @ModelAttribute("search") Search search) {

        model.addAttribute("search", search);
        model.addAttribute("products", productService.getProductListBySearch(search.getSearchRow()));
        return "search";
    }
}
