# Mocking Dependencies in Spring Tests using Mockito

Full, ready-to-run solutions for all 3 exercises, packaged as a complete Spring Boot
Maven project (H2 in-memory DB, no external setup needed).

## Structure

```
spring-mockito-dependencies-exercises/
├── pom.xml
└── src
    ├── main/java/com/example/mockitodeps
    │   ├── MockitoDepsApplication.java
    │   ├── model/User.java
    │   ├── repository/UserRepository.java
    │   ├── service/UserService.java
    │   └── controller/UserController.java
    ├── main/resources/application.properties
    └── test/java/com/example/mockitodeps
        ├── controller/UserControllerTest.java   # Exercise 1
        ├── service/UserServiceTest.java          # Exercise 2
        └── integration/UserIntegrationTest.java  # Exercise 3
```

## Exercises covered

1. **Mocking a Service Dependency in a Controller Test** – `@WebMvcTest` loads only
   the web layer for `UserController`; `UserService` is replaced with `@MockBean`.
2. **Mocking a Repository in a Service Test** – plain Mockito unit test using
   `@ExtendWith(MockitoExtension.class)`, `@Mock` for `UserRepository`, `@InjectMocks`
   for `UserService`. No Spring context involved — fastest of the three.
3. **Mocking a Service Dependency in an Integration Test** – `@SpringBootTest` +
   `@AutoConfigureMockMvc` boots the real application context and web layer, but
   `UserService` is still swapped out with `@MockBean`, so the database is never
   actually touched.

Each style is useful for a different purpose: Exercise 2 is fastest and most isolated
(pure unit test), Exercise 1 checks the controller/JSON layer without a real service,
and Exercise 3 verifies the whole Spring wiring works end-to-end while keeping the
service layer mocked for speed and determinism.

## Run the tests

```bash
mvn test
```

## Run the app

```bash
mvn spring-boot:run
```

```bash
curl http://localhost:8080/users/1
```

## Requirements

- Java 17+
- Maven 3.8+

No external database needed — H2 runs in-memory.
