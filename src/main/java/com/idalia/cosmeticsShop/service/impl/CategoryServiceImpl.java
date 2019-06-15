package com.idalia.cosmeticsShop.service.impl;

import com.idalia.cosmeticsShop.repository.CategoryRepository;
import com.idalia.cosmeticsShop.model.Category;
import com.idalia.cosmeticsShop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category FindByName(String name) {
        return categoryRepository.findByName(name);
    }

    public int id(String name) {
        return categoryRepository.findByName(name).getId();
    }

    @Override
    public String categoryName(int id) {
        return categoryRepository.findById(id).getName();
    }
}
