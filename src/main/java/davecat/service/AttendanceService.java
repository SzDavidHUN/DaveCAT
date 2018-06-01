package davecat.service;

import davecat.exceptions.AttendanceAlreadyExistsException;
import davecat.modell.Attendance;
import davecat.modell.Course;
import davecat.modell.Lesson;
import davecat.modell.User;
import davecat.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private UserService userService;

    public Attendance add(UUID courseID, UUID userID) throws AttendanceAlreadyExistsException {

        if (isExists(courseID, userID)) {
            throw new AttendanceAlreadyExistsException("Attendance already exists for: CourseID: " + courseID + " userID: " + userID);
        }
        Course course = courseService.getByID(courseID);
        User user = userService.getByID(userID);
        Attendance attendance = new Attendance(course, user);
        List<Lesson> lessons = attendance.getLessons();
        Lesson defaultLesson = lessonService.getByID(new UUID(0, 0));
        for (int i = 0; i < attendance.getLength(); i++)
            lessons.add(i, defaultLesson);
        attendanceRepository.save(attendance);
        courseService.addAttendace(course, attendance);
        userService.addAttendance(user, attendance);

        return attendance;
    } //new

    public Attendance getByID(UUID attendaceID) throws EntityNotFoundException {
        Optional<Attendance> byId = attendanceRepository.findById(attendaceID);
        if (byId.isPresent())
            return byId.get();
        else
            throw new EntityNotFoundException("Couldn't find attendace for attendaceID: " + attendaceID);
    } //new

    public Attendance getByID(UUID courseID, UUID userID) throws EntityNotFoundException {
        Iterable<Attendance> attendances = attendanceRepository.findAll();
        for (Attendance attendance : attendances
                ) {
            if (attendance.getCourse().getId().equals(courseID) && attendance.getUser().getId().equals(userID))
                return attendance;
        }
        throw new EntityNotFoundException("Couldn't find attendace for courseID: " + courseID + " UserID: " + userID);
    } //new

    public boolean isExists(UUID courseID, UUID userID) {
        try {
            getByID(courseID, userID);
            return true;
        } catch (EntityNotFoundException ex) {
            return false;
        }
    } //new

    public void remove(UUID attendanceID) {
        remove(getByID(attendanceID));
    } //OK

    public void remove(Attendance attendance) {
        courseService.removeAttendance(attendance.getCourse(), attendance);
        userService.removeAttendance(attendance.getUser(), attendance);
        attendanceRepository.delete(attendance);
    } //OK

    public void setLesson(UUID attendanceID, Integer occasion, UUID lessonID) {
        Attendance attendance = getByID(attendanceID);
        Lesson lesson = lessonService.getByID(lessonID);
        attendance.getLessons().set(occasion, lesson);
        attendanceRepository.save(attendance);
    }

    public int getPresent(UUID attendanceID) {
        return getPresent(getByID(attendanceID));
    }

    public int getPresent(Attendance attendance) {
        int count = 0;
        for (Lesson lesson : attendance.getLessons()
                ) {
            if (lesson.isPresen())
                count++;
        }
        return count;
    }

    public int getAway(UUID attendanceID) {
        return getAway(getByID(attendanceID));
    }

    public int getAway(Attendance attendance) {
        int count = 0;
        for (Lesson lesson : attendance.getLessons()
                ) {
            if (lesson.isAway())
                count++;
        }
        return count;
    }
}
