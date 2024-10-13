package summit.exceptions;

public class MemberWithSameUsernameAlreadyExistsException extends Exception {

    public MemberWithSameUsernameAlreadyExistsException(String username) {
        super("'%s' already exists".formatted(username));
    }

}
