package ru.practicum;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.dto.HitRequestDto;
import ru.practicum.dto.StatResponseDto;
import ru.practicum.model.Stat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatService {

    private final StatRepository statsRepository;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void recordRequest(HitRequestDto requestDto) {
        Stat stats = new Stat();
        stats.setApp(requestDto.getApp());
        stats.setUri(requestDto.getUri());
        stats.setIp(requestDto.getIp());
        stats.setDate(LocalDateTime.parse(requestDto.getTimestamp(), FORMATTER));
        statsRepository.save(stats);
        log.info("Сохранена статистика: app={}, uri={}, ip={}", stats.getApp(), stats.getUri(), stats.getIp());
    }

    public List<StatResponseDto> getStats(String start, String end, List<String> uris, boolean unique) {
        LocalDateTime startDate = LocalDateTime.parse(start, FORMATTER);
        LocalDateTime endDate = LocalDateTime.parse(end, FORMATTER);

        List<Stat> stats;

        if (uris != null && !uris.isEmpty()) {
            stats = statsRepository.findByUriInAndDateBetween(uris, startDate, endDate);
        } else {
            stats = statsRepository.findByDateBetween(startDate, endDate);
        }

        Map<String, Long> hitsMap;

        if (unique) {
            hitsMap = stats.stream()
                    .collect(Collectors.groupingBy(
                            s -> s.getApp() + "|" + s.getUri(),
                            Collectors.mapping(
                                    Stat::getIp,
                                    Collectors.collectingAndThen(
                                            Collectors.toSet(),
                                            set -> Long.valueOf(set.size())
                                    )
                            )
                    ));
        } else {
            hitsMap = stats.stream()
                    .collect(Collectors.groupingBy(
                            s -> s.getApp() + "|" + s.getUri(),
                            Collectors.counting()
                    ));
        }

        return hitsMap.entrySet().stream()
                .map(entry -> {
                    String[] parts = entry.getKey().split("\\|");
                    return new StatResponseDto(parts[0], parts[1], entry.getValue());
                })
                .sorted(Comparator.comparingLong(StatResponseDto::getHits).reversed())
                .collect(Collectors.toList());
    }
}