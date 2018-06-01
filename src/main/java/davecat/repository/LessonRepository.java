package davecat.repository;

import davecat.modell.Lesson;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface LessonRepository extends CrudRepository<Lesson, UUID> {

}
