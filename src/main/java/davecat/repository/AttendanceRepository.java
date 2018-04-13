package davecat.repository;

import davecat.modell.Attendance;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AttendanceRepository extends CrudRepository<Attendance, UUID> {

}
