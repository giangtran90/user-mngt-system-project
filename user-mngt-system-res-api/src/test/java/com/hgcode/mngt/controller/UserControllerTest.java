package com.hgcode.mngt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hgcode.mngt.entity.UserEntity;
import com.hgcode.mngt.model.User;
import com.hgcode.mngt.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = UserController.class)
class UserControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    UserEntity userEntityOne;
    UserEntity userEntityTwo;

    User userOne;
    User userTwo;
    List<User> userList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        userOne = new User(1L,"Tran","Giang","giang08t3@gmail.com");
        userTwo = new User(2L,"Tran","Lam","hiro@gmail.com");
        userList.add(userOne);
        userList.add(userTwo);
        userEntityOne = new UserEntity();
        userEntityTwo = new UserEntity();
        BeanUtils.copyProperties(userOne,userEntityOne);
        BeanUtils.copyProperties(userTwo,userEntityTwo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSaveUser() throws Exception {
        String requestJson = writeValueAsString(userOne);
        when(userService.saveUser(userOne)).thenReturn(userOne);
        this.mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(userList);
        this.mockMvc.perform(get("/api/v1/users"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private String writeValueAsString(User user) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);

        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(user);
    }

    @Test
    void testGetUserById() throws Exception {
        when(userService.getUserById(1L)).thenReturn(userOne);
//        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/users/1")
//                .accept(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        System.out.println(result.getResponse().getContentAsString());
//        String expected = "{id:1,firstName:Tran,lastName:Giang,emailId:giang08t3@gmail.com}";
//        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
        this.mockMvc.perform(get("/api/v1/users/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}