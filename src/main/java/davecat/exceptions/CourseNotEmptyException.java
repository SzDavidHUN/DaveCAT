package davecat.exceptions;

public class CourseNotEmptyException extends Exception {
    public CourseNotEmptyException() {
    }

    public CourseNotEmptyException(String message) {
        super(message);
    }
}
