package davecat.service;

import davecat.modell.Attendance;
import davecat.modell.Lesson;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.UUID;

public interface LessonService {
    Lesson add(String name, String description, String render, boolean present, boolean away);

    void remove(UUID lessonID);

    void remove(Lesson lesson);

    Lesson getByID(UUID lessonID) throws EntityNotFoundException;

    Collection<Lesson> getAll();

    void addAttendance(Lesson lesson, Attendance attendance);

    void removeAttendance(Lesson lesson, Attendance attendance);
}
