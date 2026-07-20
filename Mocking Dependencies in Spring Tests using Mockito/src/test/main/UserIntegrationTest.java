package com.example.mockitodeps.integration;

import com.example.mockitodeps.model.User;
import com.example.mockitodeps.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Exercise 3: Mocking a Service Dependency in an Integration Test.
 *
 * @SpringBootTest boots the full application context (as a real integration
 * test would), but @MockBean replaces UserService with a Mockito mock, so
 * the test exercises the real controller/web layer while the service layer
 * (and therefore the database) stays fully mocked out.
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetUser_withMockedService() throws Exception {
        User mockUser = new User(1L, "Integration User");
        when(userService.getUserById(1L)).thenReturn(mockUser);

        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Integration User"));
    }

    @Test
    void testGetUser_serviceReturnsNull() throws Exception {
        when(userService.getUserById(404L)).thenReturn(null);

        mockMvc.perform(get("/users/{id}", 404L))
                .andExpect(status().isOk());
    }
}
