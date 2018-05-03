package davecat.exceptions;

public class AttendanceAlreadyExistsException extends RuntimeException {
    public AttendanceAlreadyExistsException() {
    }

    public AttendanceAlreadyExistsException(String message) {
        super(message);
    }
}
