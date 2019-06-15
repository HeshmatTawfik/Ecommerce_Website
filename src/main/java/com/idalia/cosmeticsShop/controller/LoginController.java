package com.idalia.cosmeticsShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.idalia.cosmeticsShop.service.UserService;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class LoginController {


    @Autowired
    UserService userService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String root(HttpServletRequest request, Principal principal,Model model) {
        HttpSession session = request.getSession(false);

        System.out.println("login");
        String referrer = request.getHeader("Referer");
        if (referrer != null) {
            request.getSession().setAttribute("url_prior_login", referrer);

        }
        if (!model.containsAttribute("jsonCart")) {
            model.addAttribute("jsonCart", null);
        }
        return principal == null ? "login" : "redirect:/";
        // return "login";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String roo(Model model) {
        System.out.println("logon");

        return "login";
    }


}
