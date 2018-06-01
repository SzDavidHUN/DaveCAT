package davecat.modell;

import davecat.exceptions.InvalidOccasionException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne(targetEntity = User.class)
    private User user;
    @ManyToOne(targetEntity = Course.class)
    private Course course;
    @ManyToMany(targetEntity = Lesson.class)
    private List<Lesson> lessons;
    private int length;

    private void init() {
        lessons = new ArrayList<>(length);
    }

    protected Attendance() {
        init();
    }

    public Attendance(Course course, User user) {
        this.user = user;
        this.course = course;
        this.length = course.getLength();
        init();
    } //new

    public Attendance(Course course, User user, int length) {
        this.user = user;
        this.course = course;
        this.length = length;
        init();
    }

    public boolean setLesson(Lesson lesson, int occasion) {
        if (occasion >= length || occasion < 0)
            throw new InvalidOccasionException();
        lessons.add(occasion, lesson);
        return true;
    }

    //Getters


    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Course getCourse() {
        return course;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public int getLength() {
        return length;
    }
}
