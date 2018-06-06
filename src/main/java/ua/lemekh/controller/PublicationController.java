package ua.lemekh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.lemekh.Util.StringUtil;
import ua.lemekh.dao.RoleDao;
import ua.lemekh.dto.PublicationDTO;
import ua.lemekh.dto.RatingDTO;
import ua.lemekh.model.*;
import ua.lemekh.service.GroupService;
import ua.lemekh.service.CommentService;
import ua.lemekh.service.PublicationService;
import ua.lemekh.service.UserService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Ostap on 16.06.2017.
 */
@Controller
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model, Integer offset, Integer maxResults) {
        model.addAttribute("products", publicationService.getProductList(offset, maxResults)
                .stream().map(PublicationDTO::toDto).collect(Collectors.toList()));
        model.addAttribute("count", publicationService.count());
        model.addAttribute("offset", offset);
        return "home";
    }

    @RequestMapping(value = "/lot/{id}", method = RequestMethod.GET)
    public String lot(@PathVariable("id") Integer id, Model model) {
        PublicationDTO publicationDTO = publicationService.getProductDTOById(id);
        model.addAttribute("product", publicationDTO);
        model.addAttribute("rating", new RatingDTO()
                .setValue(publicationDTO.getRate())
                .setAuthority(Objects.isNull(userService.getCurrentUser())));
        model.addAttribute("show", groupService.list());
        return "lot";
    }

    @RequestMapping(value = "/lot/{id}", method = RequestMethod.POST)
    public String rate(@PathVariable("id") Integer id, RatingDTO ratingDTO) {
        publicationService.setRateForPublication(id, ratingDTO.getValue());
        return "redirect:/lot/{id}";
    }

    @RequestMapping(value = "/lot/download/{id}", method = RequestMethod.GET)
    public void downloadFile(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
        Publication publication = publicationService.getProductById(id);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + StringUtil.getNameFromPath(publication.getFileUrl()));

        File file = new File(publication.getFileUrl());
        FileInputStream fileIn = new FileInputStream(file);
        ServletOutputStream out = response.getOutputStream();

        byte[] outputByte = new byte[4096];
        while (fileIn.read(outputByte, 0, 4096) != -1) {
            out.write(outputByte, 0, 4096);
        }
        fileIn.close();
        out.flush();
        out.close();
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @RequestMapping(value = "/lot/{id}/addComment", method = RequestMethod.POST)
    @ResponseBody
    public Comments createComment(@PathVariable Integer id, @Valid @RequestBody Comments comments) {
        comments.setProducts(publicationService.getProductById(id));
        return commentService.addComment(comments);
    }
/*
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "cart", method = RequestMethod.GET)
    public String cart(Model model){
        model.addAttribute("show", groupService.list());
        return "cart";
    }
*/


    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String searchPost(Model model, @RequestParam("searchRow") String string) {
        System.out.println("search row + " + string);
        model.addAttribute("show", groupService.list());
        model.addAttribute("products", publicationService.getProductListBySearch(string));
        return "search";
    }


    /*@RequestMapping(value = "category", method = RequestMethod.GET)
    public String categoryTest(@RequestParam("name") String name, Model model, Integer offset, Integer maxResults) {
        model.addAttribute("products", publicationService.getPublicationsByGroup(name,offset, maxResults));
        model.addAttribute("count", publicationService.CountForCategory(name));
        model.addAttribute("offset", offset);
        model.addAttribute("show", groupService.list());
        return "category";
    }*/
}
