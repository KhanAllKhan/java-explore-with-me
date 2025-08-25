package ru.practicum;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Map;

@Slf4j
@Service
public class StatClient extends BaseClient {

    private static final String API_PREFIX = "";

    @Autowired
    public StatClient(@Value("${stats-service.url}") String serverUrl,
                      RestTemplateBuilder builder) {
        super(builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                .build());
    }

    public ResponseEntity<Object> recordHit(HitRequestDto hitDto) {
        return post("/hit", hitDto);
    }

    public ResponseEntity<Object> getStats(Map<String, Object> parameters) {
        return get("/stats", parameters);
    }
}
