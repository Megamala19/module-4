# Advanced Mockito Hands-On Exercises

Full, ready-to-run solutions for the 5 advanced Mockito exercises. The PDF gave solution
code that referenced classes like `Repository`, `Service`, `RestClient`, `FileReader`,
etc. without defining them — this project adds those supporting classes so everything
actually compiles and runs.

## Structure

```
mockito-advanced-exercises/
├── pom.xml
└── src
    ├── main/java/com/example/mockitoadvanced
    │   ├── Repository.java        # Exercise 1 & 5
    │   ├── Service.java            # Exercise 1 & 5
    │   ├── RestClient.java         # Exercise 2
    │   ├── ApiService.java         # Exercise 2
    │   ├── FileReader.java         # Exercise 3 (custom interface, not java.io)
    │   ├── FileWriter.java         # Exercise 3 (custom interface, not java.io)
    │   ├── FileService.java        # Exercise 3
    │   ├── NetworkClient.java      # Exercise 4
    │   └── NetworkService.java     # Exercise 4
    └── test/java/com/example/mockitoadvanced
        ├── ServiceTest.java             # Exercise 1
        ├── ApiServiceTest.java          # Exercise 2
        ├── FileServiceTest.java         # Exercise 3
        ├── NetworkServiceTest.java      # Exercise 4
        └── MultiReturnServiceTest.java  # Exercise 5
```

## Exercises covered

1. **Mocking Databases and Repositories** – mock `Repository`, stub `getData()`.
2. **Mocking External Services (RESTful APIs)** – mock `RestClient`, stub `getResponse()`.
3. **Mocking File I/O** – mock `FileReader` and `FileWriter`, verify write interaction.
4. **Mocking Network Interactions** – mock `NetworkClient`, stub `connect()`.
5. **Mocking Multiple Return Values** – chained `thenReturn()` for consecutive calls.

## Run the tests

```bash
mvn test
```

## Requirements

- Java 17+
- Maven 3.8+

## Note on `FileReader` / `FileWriter`

These are custom interfaces defined in this project (not `java.io.FileReader` /
`java.io.FileWriter`), so the exercise's solution code compiles as written against a
mockable type.
