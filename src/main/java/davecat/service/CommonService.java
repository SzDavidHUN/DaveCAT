package davecat.service;

import davecat.exceptions.AttendanceAlreadyExistsException;
import davecat.modell.Attendance;
import davecat.modell.Course;
import davecat.modell.User;
import davecat.repository.AttendanceRepository;
import davecat.repository.CourseRepository;
import davecat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommonService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private AttendanceService attendanceService;

    public boolean addCourseToUser(UUID courseID, UUID userID) {
        return addUserToCourse(userID, courseID);
    }

    public boolean addUserToCourse(UUID userID, UUID courseID) throws EntityNotFoundException {

        Optional<Course> course = courseRepository.findById(courseID);
        Optional<User> user = userRepository.findById(userID);

        if (!course.isPresent()){
            System.out.println("NULL COURSE!");
            throw new EntityNotFoundException("addUserToCourse(): Couldn't find course! (User not checked)");
        }
        if (!user.isPresent()) {
            System.out.println("NULL USER!");
            throw new EntityNotFoundException("addUserToCourse(): Couldn't find user!");
        }

        user.get().addCourse(course.get());
        course.get().addUser(user.get());

        courseRepository.save(course.get());
        userRepository.save(user.get());

        return true;
    }

    public void addAttendance(UUID courseID, UUID userID) throws AttendanceAlreadyExistsException, EntityNotFoundException {
        if(attendanceService.existAttendance(courseID, userID)){
            throw new AttendanceAlreadyExistsException();
        }
        Optional<Course> oCourse = courseRepository.findById(courseID);
        Optional<User> oUser = userRepository.findById(userID);
        if(!oCourse.isPresent() || !oUser.isPresent()){
            throw new EntityNotFoundException("addAttendance(): Couldn't find course or user!");
        }
        Course course = oCourse.get();
        User user = oUser.get();
        Attendance attendance = new Attendance(course, user, course.getLength());
        attendanceRepository.save(attendance);
        course.getUsers().add(user);
        user.getCourses().add(course);
        course.getAttendances().add(attendance);
        user.getAttendances().add(attendance);
        courseRepository.save(course);
        userRepository.save(user);
    }

    public void removeAttendance(Attendance attendance) {
        User user = attendance.getUser();
        Course course = attendance.getCourse();
        user.getCourses().remove(course);
        user.getAttendances().remove(attendance);
        userRepository.save(user);
        course.getUsers().remove(user);
        course.getAttendances().remove(attendance);
        courseRepository.save(course);
        attendanceRepository.delete(attendance);

    }
}
