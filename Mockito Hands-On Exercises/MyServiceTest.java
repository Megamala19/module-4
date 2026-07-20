package com.example.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Mockito Hands-On Exercises - full solutions.
 *
 * Dependencies (Maven):
 *   org.junit.jupiter:junit-jupiter:5.x
 *   org.mockito:mockito-core:5.x
 *   org.mockito:mockito-junit-jupiter:5.x
 */
public class MyServiceTest {

    // ---------------------------------------------------------------
    // Exercise 1: Mocking and Stubbing
    // ---------------------------------------------------------------
    @Test
    public void testExternalApi() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Mock Data");

        MyService service = new MyService(mockApi);
        String result = service.fetchData();

        assertEquals("Mock Data", result);
    }

    // ---------------------------------------------------------------
    // Exercise 2: Verifying Interactions
    // ---------------------------------------------------------------
    @Test
    public void testVerifyInteraction() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.fetchData();

        verify(mockApi).getData();
    }

    // ---------------------------------------------------------------
    // Exercise 3: Argument Matching
    // ---------------------------------------------------------------
    @Test
    public void testArgumentMatching() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.fetchDataWithArg("hello");

        // Exact argument match
        verify(mockApi).performAction("hello");

        // Matcher-based verification (any non-null string)
        verify(mockApi).performAction(anyString());

        // Matcher-based verification with a custom condition
        verify(mockApi).performAction(argThat(arg -> arg.startsWith("hel")));
    }

    // ---------------------------------------------------------------
    // Exercise 4: Handling Void Methods
    // ---------------------------------------------------------------
    @Test
    public void testVoidMethod() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);

        // Stub void method to do nothing (default), or explicitly:
        doNothing().when(mockApi).performAction(anyString());

        MyService service = new MyService(mockApi);
        service.fetchDataWithArg("world");

        verify(mockApi, times(1)).performAction("world");
    }

    // ---------------------------------------------------------------
    // Exercise 5: Mocking and Stubbing with Multiple Returns
    // ---------------------------------------------------------------
    @Test
    public void testMultipleReturns() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData())
                .thenReturn("First Call")
                .thenReturn("Second Call");

        MyService service = new MyService(mockApi);
        String result = service.fetchTwice();

        assertEquals("First Call|Second Call", result);
    }

    // ---------------------------------------------------------------
    // Exercise 6: Verifying Interaction Order
    // ---------------------------------------------------------------
    @Test
    public void testInteractionOrder() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.doOrderedWork();

        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).performAction("first");
        inOrder.verify(mockApi).performAction("second");
    }

    // ---------------------------------------------------------------
    // Exercise 7: Handling Void Methods with Exceptions
    // ---------------------------------------------------------------
    @Test
    public void testVoidMethodThrowsException() {
        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        doThrow(new RuntimeException("API failure"))
                .when(mockApi).riskyAction();

        MyService service = new MyService(mockApi);
        String result = service.safeRiskyCall();

        assertEquals("handled:API failure", result);
        verify(mockApi).riskyAction();
    }
}
