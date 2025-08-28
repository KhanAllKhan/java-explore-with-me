package ru.practicum;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.practicum.dto.EndpointHit;
import ru.practicum.dto.ViewStats;
import ru.practicum.dto.ViewsStatsRequest;

import java.sql.Timestamp;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatRepositoryImpl implements StatRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void saveHit(EndpointHit hit) {
        String sql = "INSERT INTO stats (app, uri, ip, date) " +
                "VALUES (:app, :uri, :ip, :timestamp)";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("app", hit.getApp())
                .addValue("uri", hit.getUri())
                .addValue("ip", hit.getIp())
                .addValue("timestamp", Timestamp.valueOf(hit.getTimestamp()));

        jdbcTemplate.update(sql, params);
    }

    @Override
    public List<ViewStats> getStats(ViewsStatsRequest request) {
        String sql = "SELECT app, uri, COUNT(ip) as hits " +
                "FROM stats " +
                "WHERE date BETWEEN :start AND :end " +
                "AND (:uris IS NULL OR uri IN (:uris)) " +
                "GROUP BY app, uri " +
                "ORDER BY hits DESC";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("start", Timestamp.valueOf(request.getStart()))
                .addValue("end", Timestamp.valueOf(request.getEnd()))
                .addValue("uris", request.getUris() != null && !request.getUris().isEmpty() ? request.getUris() : null);

        return jdbcTemplate.query(sql, params, (rs, rowNum) ->
                new ViewStats(
                        rs.getString("app"),
                        rs.getString("uri"),
                        rs.getLong("hits")
                ));
    }

    @Override
    public List<ViewStats> getUniqueStats(ViewsStatsRequest request) {
        String sql = "SELECT app, uri, COUNT(DISTINCT ip) as hits " +
                "FROM stats " +
                "WHERE date BETWEEN :start AND :end " +
                "AND (:uris IS NULL OR uri IN (:uris)) " +
                "GROUP BY app, uri " +
                "ORDER BY hits DESC";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("start", Timestamp.valueOf(request.getStart()))
                .addValue("end", Timestamp.valueOf(request.getEnd()))
                .addValue("uris", request.getUris() != null && !request.getUris().isEmpty() ? request.getUris() : null);

        return jdbcTemplate.query(sql, params, (rs, rowNum) ->
                new ViewStats(
                        rs.getString("app"),
                        rs.getString("uri"),
                        rs.getLong("hits")
                ));
    }
}