package com.idalia.cosmeticsShop.service;

import com.idalia.cosmeticsShop.model.User;

public interface UserService {
    User findByEmail(String email);
    User save(User user);
    User findByPhone(String phone);
    User findById(int id);

}
