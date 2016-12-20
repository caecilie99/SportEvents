package sportevent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

/*    public List<Participant> getParticipant() {
        return participant;
    }

    public void setParticipant(List<Participant> participant) {
        this.participant = participant;
    }*/

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Set<AgeGroup> getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(Set<AgeGroup> ageGroup) {
        this.ageGroup = ageGroup;
    }

    /*@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Competition))
            return false;
        Competition other = (Competition) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }*/
}