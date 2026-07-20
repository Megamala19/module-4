package com.example.mockito;

/**
 * A simple external API contract used to demonstrate Mockito mocking.
 */
public interface ExternalApi {

    String getData();

    void performAction(String input);

    void riskyAction();
}
