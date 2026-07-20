package com.example.mockitoadvanced;

/**
 * Service under test for Exercise 1 (mocking repositories) and
 * Exercise 5 (multiple return values).
 */
public class Service {

    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public String processData() {
        return "Processed " + repository.getData();
    }
}
