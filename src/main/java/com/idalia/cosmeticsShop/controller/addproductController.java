package com.idalia.cosmeticsShop.controller;

import com.idalia.cosmeticsShop.model.Category;
import com.idalia.cosmeticsShop.model.Product;
import com.idalia.cosmeticsShop.service.CategoryService;
import com.idalia.cosmeticsShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.util.List;

import static com.idalia.cosmeticsShop.model.imageUpload.saveImage;

@Controller
public class addproductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = {"/admin/addproduct"}, method = RequestMethod.GET)
    public String addProductPage(Model model) {
        List<Category> categories = categoryService.findAll();
        for (Category p : categories) {
            System.out.println(p.getName());
            System.out.println(p.getId());
        }

        model.addAttribute("Categories", categories);
        model.addAttribute("product", new Product());
        return "addproduct";
    }

    @RequestMapping(value = {"/admin/addproduct"}, method = RequestMethod.POST)
    public String addProduct(@RequestParam("imageFile") MultipartFile imageFile, @Valid Product product, BindingResult bindingResult, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "addproduct";
        }
        try {

            product.setPictureUrl(saveImage(imageFile));
            product.setCategoryId(categoryService.id(product.getCategoryName()));
            System.out.println(product.getName());
            System.out.println(product.getPictureUrl());
            System.out.println(product.getDiscount());
            System.out.println(product.getCategoryId());
            productService.save(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("add prodddd");
        return "redirect:/admin/addproduct";
    }
}
