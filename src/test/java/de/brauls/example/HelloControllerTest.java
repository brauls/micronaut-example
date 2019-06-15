package de.brauls.example;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;

@MicronautTest
class HelloControllerTest {

    @Inject
    @Client("/")
    private RxHttpClient client;

    @Test
    void requestToHelloController_returnsHelloWorld() {
        final var request = HttpRequest.GET("/hello");
        final var responseBody = client.toBlocking().retrieve(request);

        assertEquals("Hello world!", responseBody);
    }
}
