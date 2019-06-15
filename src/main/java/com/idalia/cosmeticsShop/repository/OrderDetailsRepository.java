package com.idalia.cosmeticsShop.repository;

import com.idalia.cosmeticsShop.model.OrderDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Integer> {
    List<OrderDetails> findAllByOrderId(int orderId);

    @Query("select detailAmount from OrderDetails where orderId=?1 and productId=?2")
    int quantity(int orderId, int productId);


}
