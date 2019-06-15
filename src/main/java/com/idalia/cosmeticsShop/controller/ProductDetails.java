package com.idalia.cosmeticsShop.controller;

import com.idalia.cosmeticsShop.exceptions.ResourceNotFoundException;
import com.idalia.cosmeticsShop.model.Product;
import com.idalia.cosmeticsShop.service.CategoryService;
import com.idalia.cosmeticsShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("shoppingCart")
public class ProductDetails {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;


    /*   @RequestMapping(value = {"/productdetails"},method = RequestMethod.GET)
    public String productDetailsPage(Model model){

       // model.addAttribute("pro",new Product());
        return "productdetails";
    }*/
  /*  @RequestMapping(value = {"/productdetails"},method = RequestMethod.GET)
    public String productDetails(@RequestParam("productid")int id){
        System.out.println(id);
        return "productdetails";
    }*/
    @RequestMapping(value = {"/productdetails/{id}"}, method = RequestMethod.GET)
    //@RequestMapping(value = {"/productdetails"}, method = RequestMethod.GET)
    public String productDetails(@PathVariable("id") String id,Model model) {
        try {
           int i = Integer.parseInt(id);
           Product product = productService.findById(i);
           if (product == null)
                throw new ResourceNotFoundException();
            System.out.println(product.getPictureUrl());
            System.out.println(id);
            System.out.println();
            product.setCategoryName(categoryService.categoryName(product.getCategoryId()));
            model.addAttribute("productDetail",product);
            model.addAttribute("pid",id);
            System.out.println(product.getCategoryName());
            return "productdetails";
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
        //return "productdetails";
    }
}
