package sportevent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
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
@Data
@Entity
public class Competition  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "competition_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private Double fee;

/*    @ManyToMany(mappedBy = "competition", fetch = FetchType.LAZY)
    private List<Participant> participants = new ArrayList<Participant>();*/

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;


    @OneToMany
    @JoinColumn(name = "agegroup_id")
    @JsonIgnore
    private Set<AgeGroup> ageGroup = new HashSet<AgeGroup>(); // user set to create primary key

    public Competition() {
    }

    public Competition(String name, String description, Double fee, Event event) {
        this.name = name;
        this.description = description;
        this.fee = fee;
        this.event = event;
    }
}