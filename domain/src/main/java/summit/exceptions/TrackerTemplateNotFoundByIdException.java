package summit.exceptions;

public class TrackerTemplateNotFoundByIdException extends Exception {

    public TrackerTemplateNotFoundByIdException(Integer id) {
        super("Tracker template '%s' not found".formatted(id));
    }

}
