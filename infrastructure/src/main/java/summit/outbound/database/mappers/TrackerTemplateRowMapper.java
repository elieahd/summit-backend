package summit.outbound.database.mappers;

import org.springframework.jdbc.core.RowMapper;
import summit.models.TrackerInterval;
import summit.models.TrackerTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackerTemplateRowMapper implements RowMapper<TrackerTemplate> {

    @Override
    public TrackerTemplate mapRow(ResultSet rs, int rowNum) throws SQLException {
        TrackerTemplate trackerTemplate = new TrackerTemplate();
        trackerTemplate.setId(rs.getInt("id"));
        trackerTemplate.setName(rs.getString("name"));
        trackerTemplate.setDescription(rs.getString("description"));
        trackerTemplate.setInterval(TrackerInterval.valueOf(rs.getString("interval")));
        return trackerTemplate;
    }

}
