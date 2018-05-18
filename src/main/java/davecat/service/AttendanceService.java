package davecat.service;

import davecat.modell.Attendance;
import davecat.modell.Course;
import davecat.modell.User;
import davecat.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;

    public Attendance add(UUID courseID, UUID userID){

        Course course = courseService.getByID(courseID);
        User user = userService.getUserByID(userID);
        Attendance attendance = new Attendance(course, user);
        courseService.addAttendace(course, attendance);
        userService.addAttendance(user, attendance);

        return attendance;
    } //new

    public Attendance getByID(UUID attendaceID) throws EntityNotFoundException {
        Optional<Attendance> byId = attendanceRepository.findById(attendaceID);
        if(!byId.isPresent())
            return byId.get();
        throw new EntityNotFoundException("Couldn't find attendace for attendaceID: " + attendaceID);
    } //new

    public Attendance getByID(UUID courseID, UUID userID) throws EntityNotFoundException {
        Iterable<Attendance> attendances = attendanceRepository.findAll();
        for (Attendance attendance : attendances
                ) {
            if(attendance.getCourse().getId() == courseID && attendance.getUser().getId() == userID)
                return attendance;
        }
        throw new EntityNotFoundException("Couldn't find attendace for courseID: " + courseID + " UserID: " + userID);
    } //new

    public boolean isExists(UUID courseID, UUID userID){
        try{
            getByID(courseID, userID);
            return true;
        } catch (EntityNotFoundException ex){
            return false;
        }
    } //new

    public void remove(Attendance attendance){
        courseService.removeAttendance(attendance.getCourse(), attendance);
        userService.removeAttendance(attendance.getUser(), attendance);
    } //OK

}
