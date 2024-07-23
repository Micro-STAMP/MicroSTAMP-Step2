package microstamp.step2.exception;

public class Step2InvalidConnectionException extends RuntimeException {

    public Step2InvalidConnectionException() {
        super("Invalid connection");
    }

    public Step2InvalidConnectionException(final String message) {
        super(message);
    }

}
