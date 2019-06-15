package com.idalia.cosmeticsShop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    /*    registry.addViewController("/login").setViewName("login");
        registry.addViewController("/admin/dashboard").setViewName("dashboard");
        registry.addViewController("/admin/addproduct").setViewName("addproduct");
        registry.addViewController("/products").setViewName("products");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/productdetails").setViewName("productdetails");
        registry.addViewController("/register").setViewName("registerController");
        registry.addViewController("/checkout").setViewName("checkout");
        registry.addViewController("/").setViewName("login");*/
        registry.addViewController("/").setViewName("products");
        registry.addViewController("/admin/dashboard").setViewName("dashboard");
        registry.addViewController("/admin/addproduct").setViewName("addproduct");
        registry.addViewController("/productdetails").setViewName("productdetails");
        registry.addViewController("/register").setViewName("registerController");
        registry.addViewController("/checkout").setViewName("checkout");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/products").setViewName("products");
        registry.addViewController("/settings/changepassword").setViewName("changepassword");
        registry.addViewController("/orders-history").setViewName("ordershistory");
        registry.addViewController("/admin/newOrders").setViewName("newOrdersController");
        registry.addViewController("/admin/details").setViewName("orderDetails");



    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }


}
