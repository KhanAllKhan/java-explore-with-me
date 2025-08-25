package ru.practicum;

import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Slf4j

public class BaseClient {
    protected final RestTemplate rest;

    public BaseClient(RestTemplate rest) {
        this.rest = rest;
    }

    protected <T> ResponseEntity<Object> post(String path, T body) {
        return rest.exchange(path, HttpMethod.POST, new HttpEntity<>(body, defaultHeaders()), Object.class);
    }

    protected ResponseEntity<Object> get(String path, @Nullable Map<String, Object> params) {
        return rest.exchange(path, HttpMethod.GET, new HttpEntity<>(null, defaultHeaders()), Object.class, params);
    }

    private HttpHeaders defaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return headers;
    }
}
