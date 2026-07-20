# SLF4J Logging Exercises

Full, ready-to-run solutions for all 3 SLF4J/Logback logging exercises.

## Structure

```
slf4j-logging-exercises/
├── pom.xml
└── src/main
    ├── java/com/example/logging
    │   ├── LoggingExample.java               # Exercise 1
    │   ├── ParameterizedLoggingExample.java  # Exercise 2
    │   └── AppenderLoggingExample.java       # Exercise 3
    └── resources/logback.xml                  # Exercise 3 appender config
```

## Exercises covered

1. **Logging Error Messages and Warning Levels** – `logger.error()` / `logger.warn()`,
   plus info/debug/trace for comparison.
2. **Parameterized Logging** – `{}` placeholders instead of string concatenation,
   including logging an exception alongside a parameterized message.
3. **Using Different Appenders** – `logback.xml` wires up both a console appender
   and a file appender (`app.log`) on the root logger, so every log statement
   goes to both destinations.

## A note on dependency versions

The exercise sheet specified `slf4j-api 1.7.30` / `logback-classic 1.2.3`. Those are
old releases with known CVEs, so this project uses current stable versions
(`slf4j-api 2.0.13` / `logback-classic 1.5.6`) instead — the logging API used here
(`error`, `warn`, parameterized `{}` messages) is identical across both lines, so
nothing in the exercises changes.

## Run

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.example.logging.LoggingExample"
mvn exec:java -Dexec.mainClass="com.example.logging.ParameterizedLoggingExample"
mvn exec:java -Dexec.mainClass="com.example.logging.AppenderLoggingExample"
```

(If you don't have the `exec` plugin configured, just run each class directly from
your IDE, or `mvn compile` then `java -cp target/classes:<slf4j+logback jars> <class>`.)

After running `AppenderLoggingExample`, check both the console output and the
generated `app.log` file in the project root — both should contain the same
log lines.

## Requirements

- Java 17+
- Maven 3.8+
