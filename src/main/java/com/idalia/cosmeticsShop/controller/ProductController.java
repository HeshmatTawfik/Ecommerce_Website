package com.idalia.cosmeticsShop.controller;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.idalia.cosmeticsShop.model.JsonShopCart;
import com.idalia.cosmeticsShop.model.Product;
import com.idalia.cosmeticsShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.idalia.cosmeticsShop.model.JSONArrayConverter.fromJSonToList;
import static com.idalia.cosmeticsShop.model.JSONArrayConverter.mapper;

@Controller
@SessionAttributes("shoppingCart")
public class ProductController {
    @Autowired
    ProductService productService;
    public static List<String> list = new ArrayList<>();

    @GetMapping("/")
    public String products() {
        return "redirect:/products";
    }

    @GetMapping("/products")
    //  @RequestMapping(value = {"/products"}, method = RequestMethod.GET)
    public String productPage(Model model, @ModelAttribute("shoppingCart") JsonShopCart json) {

        model.addAttribute("pro", new Product());
        list.add("save.png");
        model.addAttribute("img", list);
        List<Product> products = productService.findAll();
        mapper(products);
        model.addAttribute("products", products);
        /*remove ppp later*/
        model.addAttribute("ppp", mapper(products));
        // JsonShopCart json = new JsonShopCart();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getAuthorities());
        if (auth.getAuthorities().toString().contains("ROLE_USER")) {
            model.addAttribute("auth","yes");
        }

        return "products";
    }

    @PostMapping("/products")
    public String theCart(Model model, @ModelAttribute("cart") JsonShopCart json,
                          RedirectAttributes redirectAttributes) {
        System.out.println(json.getCartToJson());
        try {


            List<Product> testJsonObj = fromJSonToList(json.getCartToJson());
            for (Product p : testJsonObj) {
                p.setDescription("Asasas");
                System.out.println(p.getId() + " " + p.getPictureUrl() + " " + p.getPrice() + " " + p.getName() + " "
                        + p.getQuantity() + " " + p.getCategoryId());
            }

            redirectAttributes.addFlashAttribute("jsonCart", json);
            if (!model.containsAttribute("jsonCart")) {
                model.addAttribute("jsonCart", null);
            }
        } catch (UnrecognizedPropertyException e) {
            return "redirect:/products";
        } catch (IOException e) {
            return "redirect:/products";
        }
        return "redirect:/checkout";
    }

    @PostMapping("/product")
    public String theCart2(@RequestParam("cart2") String cart2, RedirectAttributes redirectAttributes) {
        System.out.println("product " + cart2);
        JsonShopCart json = new JsonShopCart();
        json.setCartToJson(cart2);
        redirectAttributes.addFlashAttribute("jsonCart", json);

        return "redirect:/checkout";
    }


    @ModelAttribute("shoppingCart")
    public JsonShopCart shoppingCart() {
        return new JsonShopCart();
    }
}
