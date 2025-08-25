package ru.practicum;


import lombok.Data;

@Data
public class HitRequestDto {
    private String app;
    private String uri;
    private String ip;
    private String timestamp;
}
