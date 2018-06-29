package davecat.repository;

import davecat.modell.Attendance;
import davecat.modell.Course;
import davecat.modell.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, UUID> {

    Optional<Attendance> findByCourseAndUser(Course course, User user);

}
