package davecat.service;

import davecat.exceptions.NotEmptyException;
import davecat.modell.Attendance;
import davecat.modell.Course;

import javax.persistence.EntityNotFoundException;
import java.time.DayOfWeek;
import java.util.Collection;
import java.util.UUID;

public interface CourseService {
    Course add(String title, String description, String location, int length, DayOfWeek day, Integer begin, Integer end);

    void remove(UUID courseID) throws NotEmptyException;

    void remove(Course course) throws NotEmptyException;

    Course getByID(UUID courseID) throws EntityNotFoundException;

    boolean isEmpty(UUID courseID);

    Collection<Course> getAll();

    Collection<Attendance> getAttendances(UUID courseID);

    Collection<Attendance> getAttendances(Course course);

    Collection<Course> getCoursesAt(int time, DayOfWeek day);
}
