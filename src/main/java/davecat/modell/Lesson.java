package davecat.modell;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(length = 127)
    private String name; //Few word description
    @Column(length = 2047)
    private String description; //Longer description
    @Column(length = 127)
    private String render; //This will be used to render the status in the HTML page
    private boolean presen; //Counts as present
    private boolean away; //Counts as away // Separate because this way you can make attendances, that aren't count
    @ManyToMany(targetEntity = Attendance.class)
    private List<Attendance> attendances;

    // CONSTRUCTORS ====================================================================================================

    protected Lesson() {
    }

    public Lesson(String name, String description, String render, boolean presen, boolean away) {
        this.name = name;
        this.description = description;
        this.render = render;
        this.presen = presen;
        this.away = away;
    }

    // GETTERS =========================================================================================================

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRender() {
        return render;
    }

    public boolean isPresen() {
        return presen;
    }

    public boolean isAway() {
        return away;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    // SETTERS =========================================================================================================

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRender(String render) {
        this.render = render;
    }

    public void setPresen(Boolean presen) {
        this.presen = presen;
    }

    public void setAway(Boolean away) {
        this.away = away;
    }
}
