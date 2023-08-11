package com.hgcode.mngt.service.impl;

import com.hgcode.mngt.entity.UserEntity;
import com.hgcode.mngt.model.User;
import com.hgcode.mngt.repository.UserRepository;
import com.hgcode.mngt.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    private UserService userService;

    AutoCloseable autoCloseable;

    UserEntity userEntity;

    User user;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository);
        user = new User(1L,"Tran","Giang","giang08t3@gmail.com");
        userEntity = new UserEntity();
        BeanUtils.copyProperties(user,userEntity);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testSaveUser() {
        mock(UserEntity.class);
        mock(UserRepository.class);

        when(userRepository.save(userEntity)).thenReturn(userEntity);

        assertThat(userService.saveUser(user)).isEqualTo(user);
    }

    @Test
    void testGetAllUsers() {
        mock(UserEntity.class);
        mock(UserRepository.class);

        when(userRepository.findAll()).thenReturn(
                new ArrayList<UserEntity>(Collections.singleton(userEntity))
        );

        assertThat(userService.getAllUsers().get(0).getFirstName()).isEqualTo(userEntity.getFirstName());
    }

    @Test
    void testGetUserById() {
        mock(UserEntity.class);
        mock(UserRepository.class);

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(userEntity));

        assertThat(userService.getUserById(1L).getFirstName()).isEqualTo(userEntity.getFirstName());
    }
}