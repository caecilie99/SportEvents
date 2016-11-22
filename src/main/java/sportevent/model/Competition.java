package sportevent.model;

import javax.persistence.*;

@Entity
public class Competition {

    @Id
    @GeneratedValue
	private int id;

    @Column(nullable = false)
	private String name;

	private String description;

	private Double fee;


	private Participant[] participant;

    @ManyToOne
    @JoinColumn(name = "Id")
	private Event event;

	private AgeGroup[] ageGroup;

}
