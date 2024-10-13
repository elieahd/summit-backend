package summit.ports.outbound;

import summit.models.Member;

import java.util.Optional;

public interface MembersInventory {

    Optional<Member> findById(Integer id);

    boolean existsByUsername(String username);

    Member create(Member member);

}
