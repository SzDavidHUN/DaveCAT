package davecat.service;

import davecat.modell.Attendance;
import davecat.modell.Course;
import davecat.modell.User;
import davecat.repository.AttendanceRepository;
import davecat.repository.CourseRepository;
import davecat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommonService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;

    public boolean addCourseToUser(UUID courseID, UUID userID) {
        return addUserToCourse(userID, courseID);
    }

    public boolean addUserToCourse(UUID userID, UUID courseID) {

        Course course = courseRepository.findOne(courseID);
        User user = userRepository.findOne(userID);

        if (course == null) {
            System.out.println("NULL COURSE!");
            return false;
        }
        if (user == null) {
            System.out.println("NULL USER!");
            return false;
        }

        user.addCourse(course);
        course.addUser(user);


        /*courseRepository.delete(course);
        userRepository.delete(user);*/
        courseRepository.save(course);
        userRepository.save(user);

        return true;
    }

    public boolean addAttendance(UUID courseID, UUID userID) {
        Course course = courseRepository.findOne(courseID);
        User user = userRepository.findOne(userID);
        Attendance attendance = new Attendance(user, course, course.getLength());
        attendanceRepository.save(attendance);
        course.getUsers().add(user);
        user.getCourses().add(course);
        course.getAttendances().add(attendance);
        user.getAttendances().add(attendance);
        courseRepository.save(course);
        userRepository.save(user);
        return true;
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
