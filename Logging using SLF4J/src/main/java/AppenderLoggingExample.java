package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 3: Using Different Appenders.
 *
 * The actual appender configuration (console + file) lives in
 * src/main/resources/logback.xml - this class just logs at a few levels so
 * you can see both appenders receive the output. Check the console AND the
 * generated app.log file after running this.
 */
public class AppenderLoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(AppenderLoggingExample.class);

    public static void main(String[] args) {
        logger.debug("Debug message - should appear in console and app.log");
        logger.info("Info message - should appear in console and app.log");
        logger.warn("Warning message - should appear in console and app.log");
        logger.error("Error message - should appear in console and app.log");
    }
}
