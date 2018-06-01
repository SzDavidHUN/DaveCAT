package davecat.exceptions;

public class InvalidOccasionException extends RuntimeException {
    public InvalidOccasionException() {
    }

    public InvalidOccasionException(String s) {
        super(s);
    }

    public InvalidOccasionException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InvalidOccasionException(Throwable throwable) {
        super(throwable);
    }

    public InvalidOccasionException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
