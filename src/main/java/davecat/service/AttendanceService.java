package davecat.service;

import davecat.modell.Attendance;
import davecat.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public Collection<Attendance> getAllStudents() {
        Collection<Attendance> ret = new ArrayList<>();
        attendanceRepository.findAll().forEach(ret::add);
        return ret;
    }

    public Collection<Attendance> getAttendacesForClass(UUID courseID) {
        Collection<Attendance> ret = new ArrayList<>();

        for (Attendance attendance : attendanceRepository.findAll()
                ) {
            if (attendance.getCourse().getId().equals(courseID))
                ret.add(attendance);
        }
        return ret;
    }

    public Collection<Attendance> getAttendancesForUser(UUID userID) {
        Collection<Attendance> ret = new ArrayList<>();

        for (Attendance attendance : attendanceRepository.findAll()
                ) {
            if (attendance.getUser().getId().equals(userId))
                ret.add(attendance);
        }
        return ret;
    }

    public Attendance getAttendance(UUID courseID, UUID userID) throws EntityNotFoundException {
        Attendance attendance;
        boolean found = false;
        for (Attendance i : attendanceRepository.findAll()
                ) {
            if (i.getUser().getId().equals(userID) && i.getCourse().getId().equals(courseID)) {
                attendance = i;
                return attendance;
            }
        }
        throw new EntityNotFoundException();
    }

    public Attendance getAttendance(UUID attendanceID) throws EntityNotFoundException {
        return attendanceRepository.findOne(attendanceID);
    }

    public void setLesson(UUID attendanceID, int lesson, Attendance.Status status) {
        Attendance attendance = attendanceRepository.findOne(attendanceID);
        ArrayList<Attendance.Status> lessons = attendance.getLessons();
        lessons.set(lesson, status);
        attendanceRepository.save(attendance);
    }
}
