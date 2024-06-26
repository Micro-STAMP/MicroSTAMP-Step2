package microstamp.step2.exception;

public class Step2OrphanException extends RuntimeException {

    public Step2OrphanException() {
        super("A Component can't be child of his child");
    }

    public Step2OrphanException(final String message) {
        super(message);
    }

}
