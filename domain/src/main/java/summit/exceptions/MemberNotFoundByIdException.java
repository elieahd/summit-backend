package summit.exceptions;

public class MemberNotFoundByIdException extends Exception {

    public MemberNotFoundByIdException(Integer id) {
        super("Member '%s' not found".formatted(id));
    }

}
