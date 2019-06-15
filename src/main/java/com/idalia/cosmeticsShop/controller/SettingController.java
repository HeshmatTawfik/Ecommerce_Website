package com.idalia.cosmeticsShop.controller;

import com.idalia.cosmeticsShop.model.User;
import com.idalia.cosmeticsShop.service.impl.UserServiceImpl;
import com.idalia.cosmeticsShop.utils.PassEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/settings")
public class SettingController {
    @Autowired
    private GlobalController globalController;
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/changepassword")
    public String changePassPage() {
        return "changepassword";
    }
    @RequestMapping(value = {"/changepassword"}, method = RequestMethod.POST)
    public String changePassword(@RequestParam("currentPassword") String currentPassword, @RequestParam("newPassword") String newPassword, Model model) {
        User user = globalController.getLoginUser();

        User changePass = userService.findByEmail(user.getEmail());
        if (!PassEncoding.getInstance().passwordEncoder.matches(currentPassword,changePass.getPassword())) {
            model.addAttribute("passw", "current password is incorrect");
            return "changepassword";
        }
        changePass.setPassword(PassEncoding.getInstance().passwordEncoder.encode(newPassword));
        userService.save(changePass);
        model.addAttribute("correctPass", "password has changed successfully");
        return "changepassword";
    }

    public static void main(String[] args) {
        System.out.println(PassEncoding.getInstance().passwordEncoder.encode("123456"));
        System.out.println(PassEncoding.getInstance().passwordEncoder.matches("123456","$2a$10$6TtBtEVeZ9MQtOimJrUl/eUgscmMk58KZQMnXpuQLIO.nC262B6FC"));
    }
}
