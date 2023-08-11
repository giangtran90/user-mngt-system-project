package com.hgcode.mngt.service.impl;

import com.hgcode.mngt.entity.UserEntity;
import com.hgcode.mngt.model.User;
import com.hgcode.mngt.repository.UserRepository;
import com.hgcode.mngt.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user,userEntity);
        userRepository.save(userEntity);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<User> userList = userEntities.stream().map(
                user -> new User(user.getId(),user.getFirstName(),user.getLastName(),user.getEmailId())
        ).collect(Collectors.toList());
        return userList;
    }

    @Override
    public User getUserById(Long id) {
        UserEntity userEntity = userRepository.findById(id).get();
        User user = new User();
        BeanUtils.copyProperties(userEntity,user);
        return user;
    }
}
