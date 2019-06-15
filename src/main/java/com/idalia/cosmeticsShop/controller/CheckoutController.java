package com.idalia.cosmeticsShop.controller;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.idalia.cosmeticsShop.model.JsonShopCart;
import com.idalia.cosmeticsShop.model.Order;
import com.idalia.cosmeticsShop.model.Product;
import com.idalia.cosmeticsShop.model.User;
//import com.idalia.cosmetsicsshop.model.*;
import com.idalia.cosmeticsShop.service.OrderDetailsService;
import com.idalia.cosmeticsShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static com.idalia.cosmeticsShop.model.JSONArrayConverter.fromJSonToList;

@Controller
@ComponentScan
//@SessionAttributes({"totalPrice", "jsonCart"})
//@SessionAttributes("totalPrice")
public class CheckoutController {
    @Autowired
    private GlobalController globalController;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailsService orderDetailsService;


    @RequestMapping(value = {"/checkout"}, method = RequestMethod.GET)
    public String checkoutPage(@ModelAttribute("jsonCart") JsonShopCart json, Model model, RedirectAttributes redirect, HttpSession session) {
        try {
            fromJSonToList(json.getCartToJson());
        } catch (Exception e) {
            System.out.println("aha");
            return "redirect:/products";
        }
        if (json.getCartToJson() == null || json.getCartToJson().equals("[]"))
            return "redirect:/products";
        //if the cart will be null i have to redirect the user agaon to products
        System.out.println("checkout controller" + json.getCartToJson());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = globalController.getLoginUser();
        try {
            List<Product> products = fromJSonToList(json.getCartToJson());

            double totalPrice = 0;
            if (products != null) {

                for (Product product : products) {
                    totalPrice += (product.getQuantity() * product.getPrice());
                }
                if (!model.containsAttribute("totalPrice")) {
                    model.addAttribute("totalPrice", totalPrice);
                }
                model.addAttribute("totalItems", products.size());
                model.addAttribute("checkoutList", products);
            } else {
                String msg = "No orders to show";
                model.addAttribute("no-orders", msg);
            }

            //redirect.addFlashAttribute("total", String.valueOf(totalPrice));
            session.setAttribute("productList", products);
            session.setAttribute("total", String.valueOf(totalPrice));
        } catch (UnrecognizedPropertyException e) {
            return "redirect:/products";
        } catch (IOException i) {
            return "redirect:/products";

        }

        return "checkout";

    }

    @RequestMapping(value = {"/checkout"}, method = RequestMethod.POST)
    public String checkout(@ModelAttribute("jsonCart") JsonShopCart json, HttpSession session) {

        String totalPrice = session.getAttribute("total").toString();
        System.out.println("ss" + totalPrice);
        System.out.println("totol " + totalPrice);
        User user = globalController.getLoginUser();
        user.setOrder(new Order());
        user.getOrder().setUserId(user.getId());
        user.getOrder().setCreatedDate(LocalDateTime.now());
        user.getOrder().setState(0);
        user.getOrder().setTotalPrice(Double.valueOf(totalPrice));
        user.getOrder().setCity(user.getCity());
        user.getOrder().setAddress(user.getStreet() + ", " + user.getPostalCode());
        int orderId = orderService.save(user.getOrder()).getId();
        List<Product> products = (List<Product>) session.getAttribute("productList");
        System.out.println(products.size());

        if (products != null) {
            orderDetailsService.save(products, orderId);

        }

        System.out.println(user.getOrder().getTotalPrice() + " " + user.getOrder().getAddress() + "  " + user.getOrder().getCreatedDate());
        System.out.println("checkout post " + json.getCartToJson());
        return "redirect:/products";
    }

    @RequestMapping(value = {"/checkout/newaddress"}, method = RequestMethod.POST)
    public String newAddress(@RequestParam("address") String address, @RequestParam("region")
            String region, @RequestParam("postal") String postal, @RequestParam(value = "email", required = false) String email
            , @ModelAttribute("jsonCart") JsonShopCart json/*, @ModelAttribute("totalPrice") double totalPrice*/, HttpSession session) {
        String totalPrice = session.getAttribute("total").toString();

        User user = globalController.getLoginUser();
        user.setOrder(new Order());
        user.getOrder().setUserId(user.getId());

        user.getOrder().setCreatedDate(LocalDateTime.now());
        user.getOrder().setState(0);
        user.getOrder().setTotalPrice(Double.valueOf(totalPrice));
        user.getOrder().setCity(region);
        user.getOrder().setAddress(address + ", " + postal);
        int orderId = orderService.save(user.getOrder()).getId();
        List<Product> products = (List<Product>) session.getAttribute("productList");
        System.out.println(products.size() + " from new");
        if (products != null) {
            orderDetailsService.save(products, orderId);


        }


        return "redirect:/products";
    }



}
