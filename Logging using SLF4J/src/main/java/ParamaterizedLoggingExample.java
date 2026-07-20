package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 2: Parameterized Logging.
 *
 * Parameterized ("{}"-placeholder) logging avoids the cost of building a
 * concatenated string when the log level is disabled, and reads more
 * cleanly than manual string concatenation.
 */
public class ParameterizedLoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    public static void main(String[] args) {
        String username = "alice";
        int loginAttempts = 3;

        // Single placeholder
        logger.info("User '{}' logged in", username);

        // Multiple placeholders
        logger.warn("User '{}' failed to log in {} times", username, loginAttempts);

        // Logging an exception alongside a parameterized message
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            logger.error("Failed to process division for user '{}'", username, e);
        }
    }
}
