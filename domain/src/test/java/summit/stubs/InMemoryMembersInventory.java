package summit.stubs;

import summit.models.Member;
import summit.ports.outbound.MembersInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static utils.DomainRandomizer.randomInteger;

public class InMemoryMembersInventory implements MembersInventory {

    private final List<Member> members;

    public InMemoryMembersInventory() {
        members = new ArrayList<>();
    }

    @Override
    public Optional<Member> findById(Integer id) {
        return members.stream()
                .filter(member -> member.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean existsByUsername(String username) {
        return members.stream()
                .anyMatch(member -> member.getUsername().equals(username));
    }

    @Override
    public Member create(Member member) {
        member.setId(randomInteger());
        members.add(member);
        return member;
    }

}
