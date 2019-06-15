package com.idalia.cosmeticsShop.controller;

import com.idalia.cosmeticsShop.model.User;
import com.idalia.cosmeticsShop.service.UserService;
import com.idalia.cosmeticsShop.utils.PassEncoding;
import com.idalia.cosmeticsShop.utils.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserService userService;

 // @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    @GetMapping
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

   // @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    @PostMapping
    public String processSignUp(@Valid User user, BindingResult bindingResult, Errors errors, Model model) {
        if (errors.hasErrors()){
            bindingResult.rejectValue("email", "error.user", "something went wrong!");

            return "register";
        }
        User register = userService.findByEmail(user.getEmail());
        if (register != null) {
            model.addAttribute("exist-email", "there is already account with this email");
            return "redirect:/register";
        }
        register = userService.findByPhone(user.getPhone());
        if (register != null) {
            model.addAttribute("exist-phone", "there is already account with this phone number");
            return "redirect:/register";
        }
        user.setPassword(PassEncoding.getInstance().passwordEncoder.encode(user.getPassword()));
        user.setRole(Roles.ROLE_USER.getValue());
        User u=
        userService.save(user);
        System.out.println(u.getId() +" name "+u.getUsername());
        return "register";
    }
}
