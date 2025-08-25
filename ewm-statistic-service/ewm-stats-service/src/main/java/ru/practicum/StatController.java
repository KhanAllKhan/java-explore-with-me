package ru.practicum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping
public class StatController {

    private final StatService statService;

    @PostMapping("/hit")
    public void recordHit(@RequestBody HitRequestDto hitRequestDto) {

        statService.recordRequest(hitRequestDto);
    }

    @GetMapping("/stats")
    public List<StatResponseDto> getStats(@RequestParam String start,
                                           @RequestParam String end,
                                           @RequestParam(required = false) List<String> uris,
                                           @RequestParam(defaultValue = "false") boolean unique) {

        return statService.getStats(start, end, uris, unique);
    }
}