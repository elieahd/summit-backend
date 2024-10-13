package summit.outbound.database.mappers;

import org.springframework.jdbc.core.RowMapper;
import summit.models.Member;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRowMapper implements RowMapper<Member> {

    @Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
        Member member = new Member();
        member.setId(rs.getInt("id"));
        member.setName(rs.getString("name"));
        member.setUsername(rs.getString("username"));
        return member;
    }

}
