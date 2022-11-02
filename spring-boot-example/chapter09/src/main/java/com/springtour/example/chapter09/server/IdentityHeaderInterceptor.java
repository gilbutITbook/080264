package com.springtour.example.chapter09.server;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class IdentityHeaderInterceptor implements ClientHttpRequestInterceptor {

    private static final String COMPONENT_HEADER_NAME = "X-COMPONENT-ID";
    private static final String COMPONENT_HEADER_VALUE = "HOTEL-API";

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        request.getHeaders().addIfAbsent(COMPONENT_HEADER_NAME, COMPONENT_HEADER_VALUE);
        return execution.execute(request, body);
    }
}
