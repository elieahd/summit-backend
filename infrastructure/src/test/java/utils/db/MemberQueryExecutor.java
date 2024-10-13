package utils.db;

import org.springframework.jdbc.core.JdbcTemplate;
import summit.models.Member;
import summit.outbound.database.mappers.MemberRowMapper;

public class MemberQueryExecutor {

    private final JdbcTemplate jdbcTemplate;

    public MemberQueryExecutor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer insert(String username, String name) {
        return jdbcTemplate.queryForObject(
                """
                        INSERT INTO member (username, name)
                        VALUES (?, ?)
                        RETURNING id
                        """,
                Integer.class,
                username,
                name
        );
    }

    public Member selectOne(Integer id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM member WHERE id = ?",
                new MemberRowMapper(),
                id
        );
    }

}
