CREATE TABLE IF NOT EXISTS stats (
    id BIGSERIAL PRIMARY KEY,
    app VARCHAR(255) NOT NULL,
    uri VARCHAR(255) NOT NULL,
    ip VARCHAR(255) NOT NULL,
    date TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

-- Создание индексов для улучшения производительности запросов
CREATE INDEX IF NOT EXISTS idx_stats_date ON stats(date);
CREATE INDEX IF NOT EXISTS idx_stats_uri ON stats(uri);
CREATE INDEX IF NOT EXISTS idx_stats_app ON stats(app);