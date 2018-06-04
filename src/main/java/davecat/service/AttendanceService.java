package davecat.service;

import davecat.exceptions.AttendanceAlreadyExistsException;
import davecat.modell.Attendance;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public interface AttendanceService {
    Attendance add(UUID courseID, UUID userID) throws AttendanceAlreadyExistsException //new
    ;

    Attendance getByID(UUID attendaceID) throws EntityNotFoundException //new
    ;

    Attendance getByID(UUID courseID, UUID userID) throws EntityNotFoundException //new
    ;

    boolean isExists(UUID courseID, UUID userID) //new
    ;

    void remove(UUID attendanceID) //OK
    ;

    void remove(Attendance attendance) //OK
    ;

    void setLesson(UUID attendanceID, Integer occasion, UUID lessonID);

    int getPresent(UUID attendanceID);

    int getPresent(Attendance attendance);

    int getAway(UUID attendanceID);

    int getAway(Attendance attendance);
}
