package summit.ports.inbound;

import summit.exceptions.MemberWithSameUsernameAlreadyExistsException;
import summit.models.Member;

public interface CreateMember {

    Member create(String username,
                  String name) throws MemberWithSameUsernameAlreadyExistsException;

}
