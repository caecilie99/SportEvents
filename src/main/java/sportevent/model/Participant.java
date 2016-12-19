package sportevent.model;

import javax.persistence.*;

/**
 * Participants can attend to a competition
 *
 * @author Birgit Reiter
 * @version 1.0
 */

@Entity
public class Participant {

	@Id
	@GeneratedValue
	private int id;

	private String lastname;

	private String firstname;

	private int year;

    @ManyToOne
    @JoinColumn(name="club_id", insertable = false, updatable = false)
	private Club club;

/*    @ManyToMany
	private Competition[] competition;*/

    @OneToOne
    @JoinColumn(name="agegroup_id")
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
