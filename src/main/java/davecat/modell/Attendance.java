package davecat.modell;

import javax.persistence.*;
import java.util.ArrayList;
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
    @Lob
    private ArrayList<Status> lessons;
    private int length;

    protected Attendance(){}

    enum Status {
        Present,
        Away
    }

    public Attendance(User user, Course course, int length) {
        this.user = user;
        this.course = course;
        this.length = length;
        lessons = new ArrayList<>(length);
    }

    public boolean setLesson(Status status, int lesson){
        if(lesson >= length || lesson < 0)
            return false; //EXCEPTION kéne
        lessons.add(lesson, status);
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

    public ArrayList<Status> getLessons() {
        return lessons;
    }

    public int getLength() {
        return length;
    }
}
