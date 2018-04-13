package davecat.service;

import davecat.modell.Attendance;
import davecat.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    public Collection<Attendance> getAllStudents(){
        Collection<Attendance> ret = new ArrayList<>();
        attendanceRepository.findAll().forEach((attendance) -> ret.add(attendance));
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

    public Collection<Attendance> getAttendacesForUser(UUID userId){
        Collection<Attendance> ret = new ArrayList<>();

        for (Attendance attendance : attendanceRepository.findAll()
                ) {
            if(attendance.getUser().getId().equals(userId))
                ret.add(attendance);
        }
        return ret;
    }
}
