package com.idalia.cosmeticsShop.service.impl;

import com.idalia.cosmeticsShop.repository.OrderDetailsRepository;
import com.idalia.cosmeticsShop.model.OrderDetails;
import com.idalia.cosmeticsShop.model.Product;
import com.idalia.cosmeticsShop.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class OrderDetailsServiceImpl implements OrderDetailsService {
    @Autowired
    OrderDetailsRepository orderDetailsRepository;
    @Override
    public void save(List<Product> products,int orderId) {
        for (Product product1:products){
            OrderDetails orderDetails=new OrderDetails();
            orderDetails.setOrderId(orderId);
            orderDetails.setProductId(product1.getId());
            orderDetails.setDetailAmount(product1.getQuantity());
            orderDetails.setDetailPrice(product1.getQuantity()*product1.getPrice());
            orderDetailsRepository.save(orderDetails);
        }

    }

    @Override
    public List<OrderDetails> findAllByOrderId(int orderId) {
        return orderDetailsRepository.findAllByOrderId(orderId);
    }

    @Override
    public int quantity(int orderId, int productId) {
        return orderDetailsRepository.quantity(orderId,productId);
    }
}
