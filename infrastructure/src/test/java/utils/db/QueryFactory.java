package utils.db;

import org.springframework.jdbc.core.JdbcTemplate;

public class QueryFactory {

    public final MemberQueryExecutor members;
    public final TrackerTemplateQueryExecutor trackerTemplates;

    public QueryFactory(JdbcTemplate jdbcTemplate) {
        this.members = new MemberQueryExecutor(jdbcTemplate);
        this.trackerTemplates = new TrackerTemplateQueryExecutor(jdbcTemplate);
    }

}
