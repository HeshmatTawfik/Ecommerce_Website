package com.idalia.cosmeticsShop.controller;

import com.idalia.cosmeticsShop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminDashboardController {
    @Autowired
    GlobalController globalController;
    @RequestMapping("/admin/dashboard")
    public String dashboard(Model model){
        User user=globalController.getLoginUser();
        model.addAttribute("admiName",user.getUsername());

        return "dashboard";
    }
}
