package ru.practicum.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StatDto {
    private String endpoint;
    private LocalDateTime date;
    private long requestsCount;

    public StatDto(String endpoint, LocalDateTime date, long requestsCount) {
        this.endpoint = endpoint;
        this.date = date;
        this.requestsCount = requestsCount;
    }
}

