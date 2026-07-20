package com.example.mockitodeps.service;

import com.example.mockitodeps.model.User;
import com.example.mockitodeps.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Exercise 2: Mocking a Repository in a Service Test.
 *
 * @ExtendWith(MockitoExtension.class) gives us plain Mockito mocks without
 * spinning up any Spring context - fast, isolated unit tests.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserById_found() {
        User mockUser = new User(1L, "Jane Doe");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        User result = userService.getUserById(1L);

        assertEquals("Jane Doe", result.getName());
        verify(userRepository).findById(1L);
    }

    @Test
    void testGetUserById_notFound_returnsNull() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        User result = userService.getUserById(99L);

        assertNull(result);
    }
}
