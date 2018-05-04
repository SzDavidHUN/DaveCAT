package davecat.modell;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String neptun;
    @Enumerated(EnumType.STRING)
    private Role role;
    @ManyToMany(targetEntity = Course.class)
    private Set<Course> courses;
    @OneToMany(targetEntity = Attendance.class)
    private Set<Attendance> attendances;

    protected User() {
        init();
    }

    public User(String name, String neptun, String email, String password, Role role) {
        this.name = name;
        this.neptun = neptun;
        this.email = email;
        this.password = password;
        this.role = role;

        init();
    }

    private void init() {
        courses = new HashSet<>();
        attendances = new HashSet<>();
    }

    public enum Role {
        ADMIN,
        TEACHER,
        STUDENT,
        GUEST
    }

    //GETTERS

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNeptun() {
        return neptun;
    }

    public Role getRole() {
        return role;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public Set<Attendance> getAttendances() {
        return attendances;
    }


    //TODO: Ránézni az annotációkra

    //SETTERS

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //MODIFIERS

    public void addCourse(Course course) {
        courses.add(course);
    }
}
