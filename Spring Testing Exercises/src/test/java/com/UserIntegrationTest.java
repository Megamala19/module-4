package com.example.springtest.integration;

import com.example.springtest.model.User;
import com.example.springtest.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Exercise 4: Integration Test with Spring Boot.
 * Loads the full application context (controller -> service -> repository ->
 * H2 database) and exercises it end to end through MockMvc.
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateAndFetchUser_endToEnd() throws Exception {
        User newUser = new User("Integration Test User");

        // Create the user via the real controller -> service -> repository -> DB
        String response = mockMvc.perform(post("/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Integration Test User"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        User created = objectMapper.readValue(response, User.class);

        // Confirm it really landed in the database
        assertTrueUserExistsInDb(created.getId());

        // Fetch it back through the real endpoint
        mockMvc.perform(get("/users/{id}", created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Integration Test User"));
    }

    private void assertTrueUserExistsInDb(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new AssertionError("User " + id + " was not persisted"));
    }
}
