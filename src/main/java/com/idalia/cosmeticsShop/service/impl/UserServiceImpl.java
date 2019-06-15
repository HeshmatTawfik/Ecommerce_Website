package com.idalia.cosmeticsShop.service.impl;

import com.idalia.cosmeticsShop.repository.UserRepository;
import com.idalia.cosmeticsShop.model.User;
import com.idalia.cosmeticsShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
@Autowired
UserRepository userRepository;
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }
}
