# Spring Testing Exercises

Full, ready-to-run solutions for all 9 JUnit / Spring Boot testing exercises,
packaged as a complete Spring Boot Maven project (with H2 in-memory DB, so it
runs with zero external setup).

## Structure

```
spring-testing-exercises/
├── pom.xml
└── src
    ├── main/java/com/example/springtest
    │   ├── SpringTestApplication.java
    │   ├── model/User.java
    │   ├── repository/UserRepository.java     # Exercise 7 custom query
    │   ├── service/CalculatorService.java      # Exercise 1 & 9
    │   ├── service/UserService.java            # Exercise 2 & 6
    │   ├── controller/UserController.java      # Exercise 3 & 5
    │   └── exception/GlobalExceptionHandler.java  # Exercise 8
    ├── main/resources/application.properties
    └── test/java/com/example/springtest
        ├── service/CalculatorServiceTest.java        # Exercise 1
        ├── service/UserServiceTest.java              # Exercise 2 & 6
        ├── controller/UserControllerTest.java        # Exercise 3, 5, 8
        ├── integration/UserIntegrationTest.java       # Exercise 4
        ├── repository/UserRepositoryTest.java         # Exercise 7
        └── service/CalculatorParameterizedTest.java   # Exercise 9
```

## Exercises covered

1. **Basic Unit Test** – plain JUnit test of `CalculatorService.add()`.
2. **Mocking a Repository** – `@ExtendWith(MockitoExtension.class)`, mock `UserRepository`.
3. **REST Controller with MockMvc** – `@WebMvcTest` + `@MockBean` for `UserController` GET.
4. **Full Spring Boot Integration Test** – `@SpringBootTest` + `@AutoConfigureMockMvc`, real H2 DB, controller → service → repository → DB.
5. **Controller POST Endpoint** – MockMvc `post()` with JSON body.
6. **Service Exception Handling** – asserts `NoSuchElementException` for a missing user.
7. **Custom Repository Query** – `@DataJpaTest` for `findByName`.
8. **Controller Exception Handling** – `@ControllerAdvice` returns 404 with a body.
9. **Parameterized Test** – `@ParameterizedTest` + `@CsvSource` on `CalculatorService.add()`.

## A deliberate change from the original exercise sheet

The original `UserService.getUserById()` used `.orElse(null)`. That gives Exercises
6 and 8 (exception handling) nothing to actually test, so it's changed to
`.orElseThrow(() -> new NoSuchElementException(...))`, which `GlobalExceptionHandler`
then converts into a `404 Not Found` response. This is called out in the code comments too.

## Run the tests

```bash
mvn test
```

## Run the app

```bash
mvn spring-boot:run
```

Then try:
```bash
curl -X POST http://localhost:8080/users -H "Content-Type: application/json" -d '{"name":"Alice"}'
curl http://localhost:8080/users/1
```

## Requirements

- Java 17+
- Maven 3.8+

No external database needed — H2 runs in-memory.
