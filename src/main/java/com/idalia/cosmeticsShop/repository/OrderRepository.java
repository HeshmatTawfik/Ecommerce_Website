package com.idalia.cosmeticsShop.repository;

import com.idalia.cosmeticsShop.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    //@Query("SELECT city, CASE WHEN (state >= 1) THEN '3' WHEN (state <= 0) THEN '2' ELSE '4' END FROM Order")
    List<Order> findAll();
    List<Order> findAllByState(int state);
    List<Order> findAllByUserIdAndState(int id,int state);
    Order findById(int id);

}
