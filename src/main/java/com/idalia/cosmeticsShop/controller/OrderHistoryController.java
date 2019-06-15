package com.idalia.cosmeticsShop.controller;

import com.idalia.cosmeticsShop.model.Order;
import com.idalia.cosmeticsShop.model.OrderDetails;
import com.idalia.cosmeticsShop.model.Product;
import com.idalia.cosmeticsShop.model.User;
import com.idalia.cosmeticsShop.service.OrderDetailsService;
import com.idalia.cosmeticsShop.service.OrderService;
import com.idalia.cosmeticsShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.*;

@Controller
public class OrderHistoryController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @Autowired
    private GlobalController globalController;
    @Autowired
    private OrderDetailsService orderDetailsService;

    /* @GetMapping(value = {"/orders-history"})
     public String orderhistoryPage(Model model) {
         User user = globalController.getLoginUser();
         //change it to 1 cuz 0 mean this orders was not delivered
         List<Order> orders = orderService.findByUserIdAndStat(user.getId(), 0);
         orders.sort(Comparator.comparing(Order::getId));
         //  List<Product> products=productService.productsOfOrder(14);
         List<Product> orderDetailsProduct = new ArrayList<>();
         for (Order order : orders) {
             orderDetailsProduct.addAll(productService.productsOfOrder(order.getId()));
         }
         System.out.println(productService.productsOfOrder(16).size());
         model.addAttribute("orderPoducts", orderDetailsProduct);
         model.addAttribute("orders", orders);
         Map<Order, List<Product>> orderProductsMap = new HashMap<>();
         for (Order order : orders) {
             orderProductsMap.put(order, productService.productsOfOrder(order.getId()));

         }
         Set< Map.Entry<Order, List<Product>>> orderProductsSet = orderProductsMap.entrySet();

         for ( Map.Entry<Order, List<Product>> me:orderProductsSet)
         {
             System.out.print(me.getKey()+":");
             for (int i=0;i<me.getValue().size();i++){
                 System.out.println(me.getValue().get(i));
             }

         }
         model.addAttribute("set",orderProductsSet);

             return "ordershistory";


     }*/


    @GetMapping(value = {"/orders-history"})
    public String orderhistoryPage(Model model) {
        User user = globalController.getLoginUser();
        //change it to 1 cuz 0 mean this orders was not delivered
        List<Order> orders = orderService.findByUserIdAndStat(user.getId(), 0);
        List<Order> productsOfOrders = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {

            orders.get(i).setProducts(productService.productsOfOrder(orders.get(i).getId()));
            List<Product> products = orders.get(i).getProducts();
            for (int j = 0; j < products.size(); j++) {
                products.get(j).setQuantity(orderDetailsService.quantity(orders.get(i).getId(), products.get(j).getId()));

            }

            productsOfOrders.add(orders.get(i));
        }
        productsOfOrders.sort(Comparator.comparing(Order::getCreatedDate));

        model.addAttribute("OrdersProductList", productsOfOrders);
        List<OrderDetails> test = orderDetailsService.findAllByOrderId(1);
        for (OrderDetails o:test){
            System.out.println(o.getProduct().getId()+" quantity "+o.getProduct().getQuantity());


        }

        return "ordershistory";

    }
}
