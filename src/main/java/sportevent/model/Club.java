package sportevent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Clubs can sign in participants for events from promoter clubs
 *
 * @author Birgit Reiter
 * @version 1.0
 */

// use Lombock to create getter and setter to reduce boilerplate
//@Data
@Getter
@Setter
@Entity
public class Club implements Serializable {

    @Id
    @GeneratedValue
    // unique identifier
    private Long id;

    // full name
    @Column(nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "club", fetch = FetchType.EAGER)
    // list of participants assigned to competitions for events
    private List<Participant> participant;

    @OneToOne
    @JoinColumn(name = "user_id")
    //@JsonIgnore
    // user information for sign in
    private User user;

    public Club() { this.name = ""; }

    public Club(String name) {
        this.name = name;
    }

}
