package summit.ports.inbound;

import summit.exceptions.MemberNotFoundByIdException;
import summit.models.Member;

public interface GetMember {

    Member getById(Integer id) throws MemberNotFoundByIdException;

}
