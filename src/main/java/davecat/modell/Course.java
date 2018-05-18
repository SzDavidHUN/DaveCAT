package davecat.modell;

import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.HashSet;
import java.util.Locale;
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
    private int length;
    @Enumerated(EnumType.ORDINAL)
    private DayOfWeek day;
    private Integer begin;
    private Integer end;

    @ManyToMany(targetEntity = Attendance.class)
    private Set<Attendance> attendances;

    @ManyToMany(targetEntity = User.class)
    private Set<User> users;

    protected Course() {
        init();
    }

    public Course(String title, String description, String location, int length, DayOfWeek day, Integer begin, Integer end) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.length = length;
        this.day = day;
        this.begin = begin;
        this.end = end;

        init();
    }

    public void init() {
        attendances = new HashSet<>();
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

    public int getLength() {
        return length;
    }

    public Set<Attendance> getAttendances() {
        return attendances;
    }

    public Set<User> getUsers() {
        return users;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public String getDayString() {
        return StringUtils.capitalize(day.getDisplayName(TextStyle.FULL_STANDALONE, new Locale.Builder().setLanguage("hu").setRegion("HU").build()));
    }

    public Integer getBegin() {
        return begin;
    }

    public Integer getEnd() {
        return end;
    }

    public String getTime() {
        StringBuilder stringBuilder = new StringBuilder(getDayString());
        stringBuilder.append(" ");
        stringBuilder.append(getBegin());
        stringBuilder.append("-");
        stringBuilder.append(getEnd());
        return stringBuilder.toString();
    }

//SETTERS

    //MODIFIERS

    public void addUser(User user) {
        users.add(user);
    } //new

    public void addAttendance(Attendance attendance){
        attendances.add(attendance);
    } //new

    public void removeUser(User user) {
        users.remove(user);
    } //new

    public void removeAttendance(Attendance attendance) {
        attendances.remove(attendance);
    } //new
}
