package davecat.service;

import davecat.exceptions.NotEmptyException;
import davecat.modell.Attendance;
import davecat.modell.Course;
import davecat.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    protected void addAttendace(Course course, Attendance attendance) {
        course.addUser(attendance.getUser());
        course.addAttendance(attendance);
        courseRepository.save(course);
    } //new

    public void removeAttendance(Course course, Attendance attendance) {
        course.removeUser(attendance.getUser());
        course.removeAttendance(attendance);
        courseRepository.save(course);
    } //new

    public Course getByID(UUID courseID) throws EntityNotFoundException {
        Optional<Course> course = courseRepository.findById(courseID);
        if (course.isPresent()) return course.get();

        throw new EntityNotFoundException("Couldn't find course by courseID: " + courseID);
    }

    public Collection<Course> getAll() {
        Collection<Course> ret = new ArrayList<>();
        courseRepository.findAll().forEach(ret::add);
        return ret;
    } //OK

    public boolean isEmpty(UUID courseID) {
        Optional<Course> course = courseRepository.findById(courseID);
        if (course.isPresent())
            return course.get().getUsers().isEmpty();
        throw new EntityNotFoundException("isEmpty(): Course couldn't be found, no fake data available!");
    } //OK

    public Collection<Attendance> getAttendances(UUID courseID) {
        return getAttendances(getByID(courseID));
    } //new

    public Collection<Attendance> getAttendances(Course course) {
        return course.getAttendances();
    } //new

    public Course add(String title, String description, String location, int length, DayOfWeek day, Integer begin, Integer end) {
        Course course = new Course(title, description, location, length, day, begin, end);
        courseRepository.save(course);
        return course;
    }

    public void remove(UUID courseID) throws NotEmptyException {
        if (isEmpty(courseID)) {
            courseRepository.deleteById(courseID);
            return;
        }
        throw new NotEmptyException("Course contains users, first delete users!");
    }

    //old

    @Deprecated
    public void saveCourse(Course course) {
        courseRepository.save(course);
    } //new: add

}
