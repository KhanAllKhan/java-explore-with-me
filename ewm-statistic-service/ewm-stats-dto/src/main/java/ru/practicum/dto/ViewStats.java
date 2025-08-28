package ru.practicum.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ViewStats {
    private String app;
    private String uri;
    private Long hits;
}