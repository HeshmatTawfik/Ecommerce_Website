package com.idalia.cosmeticsShop.controller;

import com.idalia.cosmeticsShop.exceptions.ResourceNotFoundException;
import com.idalia.cosmeticsShop.model.Order;
import com.idalia.cosmeticsShop.model.OrderDetails;
import com.idalia.cosmeticsShop.model.User;
import com.idalia.cosmeticsShop.service.OrderDetailsService;
import com.idalia.cosmeticsShop.service.OrderService;
import com.idalia.cosmeticsShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NewOrdersController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailsService orderDetailsService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/admin/newOrders"}, method = RequestMethod.GET)
    public String newOrders(Model model) {
        List<Order> newOrdersList = orderService.findAllOrdersByState(0);
        model.addAttribute("newOrdersList", newOrdersList);

        return "newOrders";
    }

    @RequestMapping(value = {"/admin/details/{id}"}, method = RequestMethod.GET)
    public String orderDetails(@PathVariable("id") String id, Model model) {
        try {
            int orderId = Integer.parseInt(id);
            Order newOrder = orderService.findById(orderId);
            List<OrderDetails> orderDetails = orderDetailsService.findAllByOrderId(orderId);
            if (orderDetails.size() < 1 || orderDetails == null)
                throw new ResourceNotFoundException();

            User orderOwner = userService.findById(newOrder.getUserId());
            model.addAttribute("newOrderDetails", orderDetails);
            model.addAttribute("newOrder", newOrder);
            model.addAttribute("orderOwner", orderOwner);

            System.out.println(orderDetails);
            return "orderDetails";
        } catch (Exception e) {
            throw new ResourceNotFoundException();

        }


    }

    @RequestMapping(value = {"/admin/acceptOrder"}, method = RequestMethod.POST)
    public String acceptOrder(Model model, @RequestParam(name = "accept") String id) {
        int orderId = Integer.parseInt(id);
        model.addAttribute("confirmOrder", "Order was accepted");
        Order order = orderService.findById(orderId);
        order.setState(1);
        orderService.save(order);

        return "redirect:/admin/details/"+id;
    }

    @RequestMapping(value = {"/admin/denyOrder"}, method = RequestMethod.POST)
    public String denyOrder( @RequestParam(name = "deny") String id, Model model) {
        System.out.println(id);
        model.addAttribute("denyOrder", "Order was denied");
        int orderId = Integer.parseInt(id);
        model.addAttribute("confirmOrder", "Order was accepted");
        Order order = orderService.findById(orderId);
        /**-1 mean that order was refused by the admin */
        order.setState(-1);
        orderService.save(order);
        return "redirect:/admin/newOrders";

    }
}
