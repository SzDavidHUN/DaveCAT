package davecat.exceptions;

public class NotEmptyException extends Exception {
    public NotEmptyException() {
    }

    public NotEmptyException(String message) {
        super(message);
    }
}
