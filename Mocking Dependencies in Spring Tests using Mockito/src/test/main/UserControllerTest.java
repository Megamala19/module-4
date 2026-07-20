package com.example.mockitodeps.controller;

import com.example.mockitodeps.model.User;
import com.example.mockitodeps.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Exercise 1: Mocking a Service Dependency in a Controller Test.
 *
 * @WebMvcTest loads only the web layer for UserController; the UserService
 * dependency is mocked with @MockBean so no real service/repository/DB
 * is touched.
 */
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetUser_returnsUserFromMockedService() throws Exception {
        User mockUser = new User(1L, "John Doe");
        when(userService.getUserById(1L)).thenReturn(mockUser);

        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testGetUser_serviceReturnsNull() throws Exception {
        when(userService.getUserById(99L)).thenReturn(null);

        mockMvc.perform(get("/users/{id}", 99L))
                .andExpect(status().isOk());
    }
}
