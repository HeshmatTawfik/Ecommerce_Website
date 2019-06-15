package com.idalia.cosmeticsShop.service.impl;

import com.idalia.cosmeticsShop.model.Product;
import com.idalia.cosmeticsShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    com.idalia.cosmeticsShop.repository.productRepository productRepository;
    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product findById(int i) {
       return  productRepository.findById(i);
    }

    @Override
    public void save(Product product) {
     productRepository.save(product);
    }

    @Override
    public List<Product> productsOfOrder(int id) {
        return productRepository.productsOfOrders(id);
    }
}
