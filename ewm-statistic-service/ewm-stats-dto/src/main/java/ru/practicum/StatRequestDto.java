package ru.practicum;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StatRequestDto {
    private String endpoint;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean unique;
}