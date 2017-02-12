package sportevent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Competitions for events
 * Participants can take part in one ccompetition
 *
 * @author Birgit Reiter
 * @version 1.0
 *
 */

// use Lombock to reduce boilerplate
//@Data
@Getter
@Setter
@Entity
public class Competition  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competition_id")
    // unique identifier
    private Long id;

    @Column(nullable = false)
    // short name
    private String name;

    // full description
    private String description;

    // fee to pay
    private Double fee;

    @ManyToMany(mappedBy = "competition", fetch = FetchType.LAZY)
    @JsonIgnore
    // participants assigned to competition
    private Set<Participant> participants = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "event_id")
    //@JsonIgnore
    // assigned event
    private Event event;

    public Competition() {
    }

    public Competition(String name, String description, Double fee, Event event) {
        this.name = name;
        this.description = description;
        this.fee = fee;
        this.event = event;
    }

}