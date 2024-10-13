package summit.usecases;

import summit.UseCase;
import summit.exceptions.MemberNotFoundByIdException;
import summit.exceptions.MemberWithSameUsernameAlreadyExistsException;
import summit.models.Member;
import summit.ports.inbound.CreateMember;
import summit.ports.inbound.GetMember;
import summit.ports.outbound.MembersInventory;

@UseCase
public class MemberUseCase implements CreateMember, GetMember {

    private final MembersInventory membersInventory;

    public MemberUseCase(MembersInventory membersInventory) {
        this.membersInventory = membersInventory;
    }

    @Override
    public Member create(String username, String name) throws MemberWithSameUsernameAlreadyExistsException {
        if (membersInventory.existsByUsername(username)) {
            throw new MemberWithSameUsernameAlreadyExistsException(username);
        }
        Member member = new Member(username, name);
        return membersInventory.create(member);
    }

    @Override
    public Member getById(Integer id) throws MemberNotFoundByIdException {
        return membersInventory.findById(id)
                .orElseThrow(() -> new MemberNotFoundByIdException(id));
    }

}
