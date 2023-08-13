package com.hgcode.mngt.service.impl;

import com.hgcode.mngt.entity.UserEntity;
import com.hgcode.mngt.model.User;
import com.hgcode.mngt.repository.UserRepository;
import com.hgcode.mngt.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

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

    @Test
    void testDeleteById() {
        mock(UserEntity.class);
        mock(UserRepository.class, Mockito.CALLS_REAL_METHODS);

        doAnswer(Answers.CALLS_REAL_METHODS).when(userRepository).deleteById(any());

        assertThat(userService.deleteById(1L)).isTrue();
    }

    @Test
    void testUpdateUserById() {
        mock(UserEntity.class);
        mock(UserRepository.class);

        User userUpdate = new User(1L,"Tran","Lam","lam@gmail.com");
        UserEntity userEntityUpdate = new UserEntity();
        BeanUtils.copyProperties(userUpdate,userEntityUpdate);

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(userEntity));
        when(userRepository.save(userEntityUpdate)).thenReturn(userEntity);

        assertThat(userService.updateUserById(1L,userUpdate)).isEqualTo(userUpdate);
    }
}