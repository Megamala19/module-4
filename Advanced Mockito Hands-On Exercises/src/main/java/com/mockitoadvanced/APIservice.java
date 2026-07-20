package com.example.mockitoadvanced;

/**
 * Service under test for Exercise 2 (mocking external RESTful APIs).
 */
public class ApiService {

    private final RestClient restClient;

    public ApiService(RestClient restClient) {
        this.restClient = restClient;
    }

    public String fetchData() {
        return "Fetched " + restClient.getResponse();
    }
}
