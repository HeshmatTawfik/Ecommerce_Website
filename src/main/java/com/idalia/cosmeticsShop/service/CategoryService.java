package com.idalia.cosmeticsShop.service;

import com.idalia.cosmeticsShop.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category FindByName (String name);
    int id(String name);
    String categoryName(int id);

}
