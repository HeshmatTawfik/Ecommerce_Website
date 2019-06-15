package com.idalia.cosmeticsShop.service.impl;

import com.idalia.cosmeticsShop.repository.OrderRepository;
import com.idalia.cosmeticsShop.model.Order;
import com.idalia.cosmeticsShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Override
    public Order save(Order order) {
       return orderRepository.save(order);
    }

    @Override
    public Order findById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findByUserIdAndStat(int id, int state) {
        return orderRepository.findAllByUserIdAndState(id,state);
    }

    @Override
    public List<Order> findAllOrdersByState(int state) {
        return orderRepository.findAllByState(state);
    }
}
