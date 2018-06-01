package davecat.exceptions;

public class AttendanceAlreadyExistsException extends RuntimeException {

    public AttendanceAlreadyExistsException() {
    }

    public AttendanceAlreadyExistsException(String s) {
        super(s);
    }

    public AttendanceAlreadyExistsException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public AttendanceAlreadyExistsException(Throwable throwable) {
        super(throwable);
    }

    public AttendanceAlreadyExistsException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
