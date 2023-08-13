package com.hgcode.mngt.service;

import com.hgcode.mngt.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    Boolean deleteById(Long id);

    User updateUserById(Long id, User user);
}
