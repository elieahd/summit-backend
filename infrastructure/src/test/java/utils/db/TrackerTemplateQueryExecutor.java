package utils.db;

import org.springframework.jdbc.core.JdbcTemplate;
import summit.models.TrackerInterval;
import summit.models.TrackerTemplate;
import summit.outbound.database.mappers.TrackerTemplateRowMapper;

public class TrackerTemplateQueryExecutor {

    private final JdbcTemplate jdbcTemplate;

    public TrackerTemplateQueryExecutor(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer insert(String name,
                          String description,
                          TrackerInterval interval) {
        return jdbcTemplate.queryForObject(
                """
                        INSERT INTO tracker_template (name, description, interval)
                        VALUES (?, ?, ?)
                        RETURNING id
                        """,
                Integer.class,
                name,
                description,
                interval.name()
        );
    }

    public TrackerTemplate selectOne(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM tracker_template WHERE id = ?",
                new TrackerTemplateRowMapper(),
                id
        );
    }


}
