package davecat.service;

import davecat.exceptions.CourseNotEmptyException;
import davecat.modell.Course;
import davecat.modell.User;
import davecat.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Collection<Course> getAllCourses() {
        Collection<Course> ret = new ArrayList<>();
        courseRepository.findAll().forEach(ret::add);
        return ret;
    }

    public Course getCourseByID(UUID id) {
        Course course = courseRepository.findOne(id);
        if (course != null) return course;
        return new Course(
                /*new UUID(0,0),*/
                "Error: Class Not Found",
                "The specified class couldn't be found. This is a placeholder while I creat an exception.",
                "Nowhere",
                "Never",
                0,
                DayOfWeek.FRIDAY,
                0,
                23
        );
    }

    public Set<User> getStudentsForCourse(UUID id) {
        return courseRepository.findOne(id).getUsers();
    }

    public String getDueString() {
        return null;
    }

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public boolean isCourseEmpty(UUID courseID) {
        return courseRepository.findOne(courseID).getUsers().isEmpty();
    }

    public void removeCourse(UUID courseID) throws CourseNotEmptyException {
        if (isCourseEmpty(courseID)) {
            courseRepository.delete(courseID);
            return;
        }
        throw new CourseNotEmptyException("Course contains users, first delete users!");
    }

}
