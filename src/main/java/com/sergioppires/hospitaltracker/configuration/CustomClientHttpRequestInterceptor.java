package com.sergioppires.hospitaltracker.configuration;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
    httpRequest.getHeaders().setContentType(MediaType.APPLICATION_JSON);
    httpRequest.getHeaders().set("Authorization", "Basic dXNlci1hcGktbGVpdG9zOmFRYkxMM1pTdGFUcjM4dGo=");
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
