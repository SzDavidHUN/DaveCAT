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

    public User add(String userName, String userNeptun, String userEmail, String userPassword){
        User user = new User(
                userName,
                userNeptun,
                userEmail,
                userPassword,
                User.Role.STUDENT
        );
        userRepository.save(user);
        return user;
    } //OK

    public void remove(User user) throws NotEmptyException {
        if(user.getCourses().isEmpty()) {
            userRepository.delete(user);
            return;
        }
        throw new NotEmptyException("User still connects to attendances! userID: " + user.getId());
    }

    protected void addAttendance(User user, Attendance attendance) {
        user.addCourse(attendance.getCourse());
        user.addAttendace(attendance);
        userRepository.save(user);
     } //NEW

    public void removeAttendance(User user, Attendance attendance) {
        user.removeCourse(attendance.getCourse());
        user.removeAttendace(attendance);
        userRepository.save(user);
    } //NEW

    public User getUserByID(UUID userID) throws EntityNotFoundException {
        Optional<User> user = userRepository.findById(userID);
        if (user.isPresent()) return user.get();
        throw new EntityNotFoundException("Couldn't find user by userID: " + userID);
    } //FIX


    //old

    public Collection<User> getAllUsers() {
        Collection<User> ret = new ArrayList<>();
        userRepository.findAll().forEach(ret::add);
        return ret;
    }

    @Deprecated
    public void add(User user){
        userRepository.save(user);
    }
}
