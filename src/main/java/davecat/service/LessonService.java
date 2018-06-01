package davecat.service;

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
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    public Lesson add(String name, String description, String render, boolean present, boolean away) {
        Lesson lesson = new Lesson(name, description, render, present, away);
        lessonRepository.save(lesson);
        return lesson;
    }

    public void remove(UUID lessonID) {
        remove(getByID(lessonID));
    }

    public void remove(Lesson lesson) {
        lessonRepository.delete(lesson);
    }

    public Lesson getByID(UUID lessonID) throws EntityNotFoundException {
        Optional<Lesson> lesson = lessonRepository.findById(lessonID);
        if (lesson.isPresent())
            return lesson.get();
        else
            throw new EntityNotFoundException("Couldn't find lesson by lessonID: " + lessonID);
    }

    public Collection<Lesson> getAll() {
        Collection<Lesson> lessons = new ArrayList<>();
        lessonRepository.findAll().forEach(lessons::add);

        return lessons;
    }

}
