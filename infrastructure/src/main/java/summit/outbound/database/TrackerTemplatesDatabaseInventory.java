package summit.outbound.database;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import summit.models.TrackerTemplate;
import summit.outbound.OutboundAdapter;
import summit.outbound.database.mappers.TrackerTemplateRowMapper;
import summit.ports.outbound.TrackerTemplateInventory;

import java.util.Optional;

@OutboundAdapter
public class TrackerTemplatesDatabaseInventory implements TrackerTemplateInventory {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<TrackerTemplate> trackerTemplaterRowMapper;

    public TrackerTemplatesDatabaseInventory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.trackerTemplaterRowMapper = new TrackerTemplateRowMapper();
    }

    @Override
    public TrackerTemplate create(TrackerTemplate trackerTemplate) {
        Integer id = jdbcTemplate.queryForObject(
                """
                        INSERT INTO tracker_template (name, description, interval)
                        VALUES (?, ?, ?)
                        RETURNING id
                        """,
                Integer.class,
                trackerTemplate.getName(),
                trackerTemplate.getDescription(),
                trackerTemplate.getInterval() == null ? null : trackerTemplate.getInterval().name()
        );
        trackerTemplate.setId(id);
        return trackerTemplate;
    }

    @Override
    public Optional<TrackerTemplate> findById(Integer id) {
        try {
            TrackerTemplate trackerTemplate = jdbcTemplate.queryForObject(
                    "SELECT * FROM tracker_template WHERE id = ?",
                    trackerTemplaterRowMapper,
                    id
            );
            return Optional.ofNullable(trackerTemplate);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
