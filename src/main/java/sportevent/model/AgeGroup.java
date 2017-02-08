package sportevent.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * AgeGroups are definded by fromYear and toYear and could be assigned to competitions
 * Used to define restrictions/time limits the age of participants for competitions (marathon is not allowed for participants < 20 years)
 *
 * @author Birgit Reiter
 * @version 1.0
 */

// use Lombock to create getter and setter to reduce boilerplate
//@Data
@Getter
@Setter
@Entity
public class AgeGroup {

    @Id
    @GeneratedValue
    // unique identifier
    private int id;

    // short identifier
    @Column(nullable = false)
    private String shortcut;

    // long name
    @Column(nullable = false)
    private String name;

    // lower limit
    @Column(nullable = false)
    private int fromYear;

    // upper limit
    @Column(nullable = false)
    private int toYear;

    public AgeGroup() {
    }

    public AgeGroup(String shorcut, String name, int fromYear, int toYear) {
        this.shortcut = shorcut;
        this.name = name;
        this.fromYear = fromYear;
        this.toYear = toYear;
    }

}
