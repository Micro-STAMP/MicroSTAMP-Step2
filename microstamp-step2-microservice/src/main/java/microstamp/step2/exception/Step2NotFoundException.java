package microstamp.step2.exception;

public class Step2NotFoundException extends RuntimeException {

    public Step2NotFoundException() {
        super("Not Found");
    }

    public Step2NotFoundException(final String message) {
        super(message);
    }

}
