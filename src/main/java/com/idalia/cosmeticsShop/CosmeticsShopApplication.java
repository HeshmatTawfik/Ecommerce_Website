package com.idalia.cosmeticsShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CosmeticsShopApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(CosmeticsShopApplication.class, args);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/photos/**").addResourceLocations("file:E:/spring-projects/cosmaticphotos/");



    }
}
