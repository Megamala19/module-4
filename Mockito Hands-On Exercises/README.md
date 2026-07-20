# Mockito Hands-On Exercises

Full solutions for all 7 Mockito exercises, packaged as a ready-to-run Maven project.

## Structure

```
mockito-exercises/
├── pom.xml
└── src
    ├── main/java/com/example/mockito
    │   ├── ExternalApi.java   # interface being mocked
    │   └── MyService.java     # class under test
    └── test/java/com/example/mockito
        └── MyServiceTest.java # all 7 exercises
```

## Exercises covered

1. **Mocking and Stubbing** – mock `ExternalApi`, stub `getData()`.
2. **Verifying Interactions** – verify `getData()` was called.
3. **Argument Matching** – exact match, `anyString()`, and `argThat()`.
4. **Handling Void Methods** – `doNothing()` + verify call count.
5. **Multiple Returns** – chained `thenReturn()` for consecutive calls.
6. **Verifying Interaction Order** – `InOrder` verification.
7. **Void Methods with Exceptions** – `doThrow()` and handling the exception.

## Run the tests

```bash
mvn test
```

## Requirements

- Java 17+
- Maven 3.8+
