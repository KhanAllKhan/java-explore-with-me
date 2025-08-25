package ru.practicum;

import lombok.Data;

import java.time.LocalDateTime;
@Data

public class StatRequest {
    private String endpoint;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}