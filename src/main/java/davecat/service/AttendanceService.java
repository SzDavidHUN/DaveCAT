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

    public Collection<Attendance> getAllStudents(){
        Collection<Attendance> ret = new ArrayList<>();
        attendanceRepository.findAll().forEach(ret::add);
        return ret;
    }

    public Collection<Attendance> getAttendacesForClass(UUID courseId){
        Collection<Attendance> ret = new ArrayList<>();

        for (Attendance attendance : attendanceRepository.findAll()
             ) {
            if(attendance.getCourse().getId().equals(courseId))
                ret.add(attendance);
        }
        return ret;
    }

    public Collection<Attendance> getAttendancesForUser(UUID userId) {
        Collection<Attendance> ret = new ArrayList<>();

        for (Attendance attendance : attendanceRepository.findAll()
                ) {
            if(attendance.getUser().getId().equals(userId))
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

    public boolean removeAttendance(UUID courseID, UUID userID) {
        return removeAttendance(getAttendance(userID, courseID));
    }

    public boolean removeAttendance(UUID attendanceID) {
        return removeAttendance(attendanceRepository.findOne(attendanceID));
    }

    public boolean removeAttendance(Attendance attendance) {
        try {
            attendance.getCourse().getUsers().remove(attendance.getUser());
            attendance.getCourse().getAttendances().remove(attendance);
            attendance.getUser().getCourses().remove(attendance.getCourse());
            attendance.getUser().getAttendances().remove(attendance);
        } catch (EntityNotFoundException e) {
            return false;
        }
        return true;
    }

}
