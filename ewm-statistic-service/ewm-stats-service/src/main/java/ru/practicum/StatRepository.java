package ru.practicum;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.model.Stat;

import java.time.LocalDateTime;
import java.util.List;

public interface StatRepository extends JpaRepository<Stat, Long> {
    List<Stat> findByDateBetween(LocalDateTime start, LocalDateTime end);

    List<Stat> findByUriInAndDateBetween(List<String> uris, LocalDateTime start, LocalDateTime end);
}