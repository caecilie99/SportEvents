package sportevent.model;

import javax.persistence.*;

@Entity
public class Participant {

	@Id
	@GeneratedValue
	private int id;

	private String lastname;

	private String firstname;

	private int year;

    @ManyToOne
    @JoinColumn(name="id", insertable = false, updatable = false)
	private Club club;

/*    @ManyToMany
	private Competition[] competition;*/

    @OneToOne
    @JoinColumn(name="id")
	private AgeGroup ageGroup;

	public Participant() {
	}

	public Participant(String lastname, String firstname, int year, Club club) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.year = year;
		this.club = club;
	}


}
