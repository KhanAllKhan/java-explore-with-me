package ru.practicum.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatResponseDto {
    private String app;
    private String uri;
    private long hits;
}

