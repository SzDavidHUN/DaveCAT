package davecat.repository;

import davecat.modell.Attendance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance, UUID> {

}
