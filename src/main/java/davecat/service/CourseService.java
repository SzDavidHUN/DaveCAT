package davecat.service;

import davecat.modell.Course;
import davecat.modell.User;
import davecat.repository.AttendanceRepository;
import davecat.repository.CourseRepository;
import davecat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Collection<Course> getAllCourses(){
        Collection<Course> ret = new ArrayList<>();
        courseRepository.findAll().forEach(ret::add);
        return ret;
    }

    public Course getCourseByID(UUID id){
        Course course = courseRepository.findOne(id);
        if (course!=null) return course;
        return new Course(
                /*new UUID(0,0),*/
                "Error: Class Not Found",
                "The specified class couldn't be found. This is a placeholder while I creat an exception.",
                "Nowhere",
                "Never",
                0
        );
    }

    public Set<User> getStudentsForCourse(UUID id){
        return courseRepository.findOne(id).getUsers();
    }

}
