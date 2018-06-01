package davecat.service;

import davecat.exceptions.NotEmptyException;
import davecat.modell.Attendance;
import davecat.modell.User;
import davecat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User add(String name, String neptun, String email, String password) {
        User user = new User(
                name,
                neptun,
                email,
                password,
                User.Role.STUDENT
        );
        userRepository.save(user);
        return user;
    }

    public void remove(UUID userID) throws NotEmptyException {
        remove(getByID(userID));
    }

    public void remove(User user) throws NotEmptyException {
        if (user.getCourses().isEmpty()) {
            userRepository.delete(user);
            return;
        }
        throw new NotEmptyException("User still connects to attendances! userID: " + user.getId());
    }

    public User getByID(UUID userID) throws EntityNotFoundException {
        Optional<User> user = userRepository.findById(userID);
        if (user.isPresent()) return user.get();
        throw new EntityNotFoundException("Couldn't find user by userID: " + userID);
    }

    public boolean isEmpty(UUID userID) throws EntityNotFoundException {
        return isEmpty(getByID(userID));
    }

    public boolean isEmpty(User user) {
        return user.getCourses().isEmpty();
    }

    public Collection<Attendance> getAttendances(UUID userID) {
        return getAttendances(getByID(userID));
    }

    public Collection<Attendance> getAttendances(User user) {
        return user.getAttendances();
    }

    public Collection<User> getAllByNeptun(String neptun) {
        return userRepository.findAllByNeptun(neptun);
    }

    //PACKAGE-PRIVATE

    void addAttendance(User user, Attendance attendance) {
        user.addCourse(attendance.getCourse());
        user.addAttendace(attendance);
        userRepository.save(user);
    }

    void removeAttendance(User user, Attendance attendance) {
        user.removeCourse(attendance.getCourse());
        user.removeAttendace(attendance);
        userRepository.save(user);
    }

    public Collection<User> getAll() {
        Collection<User> ret = new ArrayList<>();
        userRepository.findAll().forEach(ret::add);
        return ret;
    }

}
