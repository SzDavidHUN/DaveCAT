package davecat.service;

import davecat.exceptions.CourseNotEmptyException;
import davecat.modell.Course;
import davecat.modell.User;
import davecat.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.DayOfWeek;
import java.util.*;

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
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) return course.get();
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

    public Set<User> getStudentsForCourse(UUID id) throws EntityNotFoundException {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent())
            course.get().getUsers();
        throw new EntityNotFoundException("getStudentsForCourse(): Course couldn't be found, fake data available!");
    }

    public String getDueString() { //TODO: Implement
        return null;
    }

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public boolean isCourseEmpty(UUID courseID) {
        Optional<Course> course = courseRepository.findById(courseID);
        if(course.isPresent())
            course.get().getUsers().isEmpty();
        throw new EntityNotFoundException("isCourseEmpty(): Course couldn't be found, fake data available!");
    }

    public void removeCourse(UUID courseID) throws CourseNotEmptyException {
        if (isCourseEmpty(courseID)) {
            courseRepository.deleteById(courseID);
            return;
        }
        throw new CourseNotEmptyException("Course contains users, first delete users!");
    }

}
