package sportevent.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Competition {

    @Id
    @GeneratedValue
	private int id;

    @Column(nullable = false)
	private String name;

	private String description;

	private Double fee;

	@OneToMany
	@JoinColumn(name = "id")
	private List<Participant> participant = new ArrayList<Participant>();

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
	private Event event;


	@OneToMany
	@JoinColumn(name = "id")
	private List<AgeGroup> ageGroup = new ArrayList<AgeGroup>();

}
