package davecat.modell;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String name; //Few word description
    String description; //Longer description
    String render; //This will be used to render the status in the HTML page
    boolean presen; //Counts as present
    boolean away; //Counts as away // Separate because this way you can make attendances, that aren't count

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

    public boolean getPresen() {
        return presen;
    }

    public boolean getAway() {
        return away;
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
