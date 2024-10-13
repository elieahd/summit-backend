package summit.outbound.database;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import summit.outbound.OutboundAdapter;
import summit.models.Member;
import summit.outbound.database.mappers.MemberRowMapper;
import summit.ports.outbound.MembersInventory;

import java.util.Optional;

@OutboundAdapter
public class MembersDatabaseInventory implements MembersInventory {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Member> memberRowMapper;

    public MembersDatabaseInventory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.memberRowMapper = new MemberRowMapper();
    }

    @Override
    public Member create(Member member) {
        Integer id = jdbcTemplate.queryForObject(
                """
                        INSERT INTO member (username, name)
                        VALUES (?, ?)
                        RETURNING id
                        """,
                Integer.class,
                member.getUsername(),
                member.getName()
        );
        member.setId(id);
        return member;
    }

    @Override
    public boolean existsByUsername(String username) {
        Boolean exists = jdbcTemplate.queryForObject(
                "SELECT EXISTS(SELECT 1 FROM member WHERE username = ?)",
                Boolean.class,
                username
        );
        return Boolean.TRUE.equals(exists);
    }

    @Override
    public Optional<Member> findById(Integer id) {
        try {
            Member member = jdbcTemplate.queryForObject(
                    "SELECT * FROM member WHERE id = ?",
                    memberRowMapper,
                    id
            );
            return Optional.ofNullable(member);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

}
