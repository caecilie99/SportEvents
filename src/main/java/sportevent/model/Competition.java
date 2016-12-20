package sportevent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Competitions for events
 * Participants can take part in one ccompetition
 *
 * @author Birgit Reiter
 * @version 1.0
 *
 */
@Entity
public class Competition {

    @Id
    @GeneratedValue
	private Long id;

    @Column(nullable = false)
	private String name;

	private String description;

	private Double fee;

	@OneToMany
	@JoinColumn(name = "participant_id")
    @JsonIgnore
	private List<Participant> participant = new ArrayList<Participant>();

    @ManyToOne
    @JoinColumn(name = "event_id")
	private Event event;


	@OneToMany
	@JoinColumn(name = "agegroup_id")
    @JsonIgnore
	private List<AgeGroup> ageGroup = new ArrayList<AgeGroup>();

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

    public List<Participant> getParticipant() {
        return participant;
    }

    public void setParticipant(List<Participant> participant) {
        this.participant = participant;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<AgeGroup> getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(List<AgeGroup> ageGroup) {
        this.ageGroup = ageGroup;
    }
}
