package davecat.service;

import davecat.modell.User;
import davecat.repository.CourseRepository;
import davecat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Collection<User> getAllUsers() {
        Collection<User> ret = new ArrayList<>();
        userRepository.findAll().forEach(ret::add);
        return ret;
    }

    public User getUserByID(UUID id) {
        User user = userRepository.findOne(id);
        if (user != null) return user;
        return new User(
                "User not found",
                "-404--",
                "null@null.null",
                "",
                User.Role.GUEST
        );
    }
}
