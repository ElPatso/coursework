package ua.lemekh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.lemekh.model.Search;

/**
 * Created by Ostap on 20.06.2017.
 */
@Controller
public class Admintest {
    @Autowired
    Search search;

    @RequestMapping(value = "createLot", method = RequestMethod.GET)
    public String createLot(Model model) {
        model.addAttribute("search", search);
        return "createLot";
    }

}
