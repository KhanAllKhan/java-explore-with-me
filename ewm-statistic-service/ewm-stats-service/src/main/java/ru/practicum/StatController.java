package ru.practicum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dto.HitRequestDto;
import ru.practicum.dto.StatResponseDto;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping
public class StatController {

    private final StatService statsService;

    @PostMapping("/hit")
    public void recordHit(@RequestBody HitRequestDto hitRequestDto) {
        log.info("Получен запрос POST /hit: {}", hitRequestDto);
        statsService.recordRequest(hitRequestDto);
    }

    @GetMapping("/stats")
    public List<StatResponseDto> getStats(@RequestParam String start,
                                          @RequestParam String end,
                                          @RequestParam(required = false) List<String> uris,
                                          @RequestParam(defaultValue = "false") boolean unique) {
        log.info("Получен запрос GET /stats со start={}, end={}, uris={}, unique={}", start, end, uris, unique);
        return statsService.getStats(start, end, uris, unique);
    }
}