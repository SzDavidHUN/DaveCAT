package davecat.modell;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    private String description;
    private String location;
    private String time;
    private int length;

    protected Course(){}

    @ManyToMany(targetEntity = Attendance.class)
    private Set<Attendance> attendaces;

    @ManyToMany(targetEntity = User.class)
    private Set<User> users;

    public Course(String title, String description, String location, String time, int length) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.time = time;
        this.length = length;

        attendaces = new HashSet<>();
        users = new HashSet<>();
    }

    //GETTERS


    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public int getLength() {
        return length;
    }

    public Set<Attendance> getAttendaces() {
        return attendaces;
    }

    public Set<User> getUsers() {
        return users;
    }

    //SETTERS

    //MODIFIERS

    public void addUser(User user){
        users.add(user);
    }
}
