package com.idalia.cosmeticsShop.repository;

import com.idalia.cosmeticsShop.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface productRepository extends CrudRepository<Product, Integer> {
    Product findById(int id);

    @Query("select  p from Product p inner join OrderDetails o on p.id=o.productId  where o.orderId=?1")
    List<Product> productsOfOrders(int id);
    //@Query("select  p.quantity from Product p inner join OrderDetails o on p.id=o.productId  where o.orderId=?1")
//    List<Integer> ttt(int id);



}
