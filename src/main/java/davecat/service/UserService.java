package davecat.service;

import davecat.modell.User;
import davecat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
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
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) return user.get();
        return new User(
                "User not found",
                "-404--",
                "null@null.null",
                "",
                User.Role.GUEST
        );
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void saveUser(String userName, String userNeptun, String userEmail, String userPassword){
        User user = new User(
                        userName,
                        userNeptun,
                        userEmail,
                        userPassword,
                        User.Role.STUDENT
                );
        userRepository.save(user);
    }
}
