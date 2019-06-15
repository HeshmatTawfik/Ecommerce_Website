package com.idalia.cosmeticsShop.repository;

import com.idalia.cosmeticsShop.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    Category findByName(String name);

    Category findById(int id);
}
