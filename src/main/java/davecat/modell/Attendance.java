package davecat.modell;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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

    protected Attendance() {
        init();
    }

    public enum Status {
        EMPTY {
            @Override
            public String toString() { //TODO: STRING ENUM
                return " ";
            }
        },
        PRESENT {
            @Override
            public String toString() {
                return "+";
            }
        },
        AWAY {
            @Override
            public String toString() {
                return "-";
            }
        }
    }

    public Attendance(User user, Course course, int length) {
        this.user = user;
        this.course = course;
        this.length = length;
        init();
    }

    private void init() {
        lessons = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            lessons.add(i, Status.EMPTY);
        }
    }

    public boolean setLesson(Status status, int lesson) {
        if (lesson >= length || lesson < 0)
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
