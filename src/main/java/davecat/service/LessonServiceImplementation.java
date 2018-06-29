package davecat.service;

import davecat.modell.Attendance;
import davecat.modell.Lesson;
import davecat.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class LessonServiceImplementation implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public Lesson add(String name, String description, String render, boolean present, boolean away) {
        Lesson lesson = new Lesson(name, description, render, present, away);
        lessonRepository.save(lesson);
        return lesson;
    }

    @Override
    public void remove(UUID lessonID) {
        remove(getByID(lessonID));
    }

    @Override
    public void remove(Lesson lesson) {
        lessonRepository.delete(lesson);
    }

    @Override
    public Lesson getByID(UUID lessonID) throws EntityNotFoundException {
        Optional<Lesson> lesson = lessonRepository.findById(lessonID);
        if (lesson.isPresent())
            return lesson.get();
        else
            throw new EntityNotFoundException("Couldn't find lesson by lessonID: " + lessonID);
    }

    @Override
    public Collection<Lesson> getAll() {
        Collection<Lesson> lessons = new ArrayList<>();
        lessonRepository.findAll().forEach(lessons::add);

        return lessons;
    }

//    @Override
    public void addAttendance(Lesson lesson, Attendance attendance){
        if(lesson.getId().equals(new UUID(0,0)))
            return;
        lesson.getAttendances().add(attendance);
        lessonRepository.save(lesson);
    }

    @Override
    public void removeAttendance(Lesson lesson, Attendance attendance) {
        lesson.getAttendances().remove(attendance);
        lessonRepository.save(lesson);
    }

}
