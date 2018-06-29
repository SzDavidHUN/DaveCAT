package davecat.exceptions;

public class NotEmptyException extends RuntimeException {

    public NotEmptyException() {
    }

    public NotEmptyException(String s) {
        super(s);
    }

    public NotEmptyException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public NotEmptyException(Throwable throwable) {
        super(throwable);
    }

    public NotEmptyException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
