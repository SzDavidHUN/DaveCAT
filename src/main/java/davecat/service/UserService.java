package davecat.service;

import davecat.exceptions.NotEmptyException;
import davecat.modell.Attendance;
import davecat.modell.User;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.UUID;

public interface UserService {
    User add(String name, String neptun, String email, String password);

    void remove(UUID userID) throws NotEmptyException;

    void remove(User user) throws NotEmptyException;

    User getByID(UUID userID) throws EntityNotFoundException;

    boolean isEmpty(UUID userID) throws EntityNotFoundException;

    boolean isEmpty(User user);

    Collection<Attendance> getAttendances(UUID userID);

    Collection<Attendance> getAttendances(User user);

    Collection<User> getAllByNeptun(String neptun);

    Collection<User> getAll();
}
