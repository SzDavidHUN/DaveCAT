package davecat.repository;

import davecat.modell.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.UUID;

@Repository
public interface CourseRepository extends CrudRepository<Course, UUID> {

    Collection<Course> findByBeginLessThanEqualAndEndGreaterThanEqualAndDay(int time, int time2, DayOfWeek day);
}
