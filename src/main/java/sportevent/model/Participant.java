package sportevent.model;

import javax.persistence.*;

@Entity
public class Participant {

	private int id;

	private String lastname;

	private String firstname;

	private int year;

    @ManyToOne
    @JoinColumn(name="Id")
	private Club registration;

    @ManyToMany
	private Competition[] competition;

    @OneToOne
    @JoinColumn(name="Id")
	private AgeGroup ageGroup;

}
