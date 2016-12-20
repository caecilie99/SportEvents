package sportevent.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Participants can attend to a competition
 *
 * @author Birgit Reiter
 * @version 1.0
 */

@Entity
public class Participant  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="participant_id")
	private Long id;

	private String lastname;

	private String firstname;

	private Long year;

    @ManyToOne
    @JoinColumn(name="club_id", insertable = false, updatable = false)
	private Club club;

	@NotEmpty
	@ManyToMany(/*cascade = CascadeType.ALL, fetch = FetchType.LAZY*/)
	@JoinTable(	joinColumns = {@JoinColumn(name = "participant_id", referencedColumnName = "participant_id")},
			inverseJoinColumns = {@JoinColumn(name = "competition_id", referencedColumnName = "competition_id")})
	private Set<Competition> competition = new HashSet<Competition>();

    @OneToOne
    @JoinColumn(name="agegroup_id")
	private AgeGroup ageGroup;

	public Participant() {
	}

	public Participant(String lastname, String firstname, Long year, Club club) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.year = year;
		this.club = club;
	}

	public Participant(String lastname, String firstname, Long year, Club club, Set<Competition> competitions) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.year = year;
		this.club = club;
		this.competition = competitions;
	}

	public Long getId() {
		return id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public AgeGroup getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(AgeGroup ageGroup) {
		this.ageGroup = ageGroup;
	}

/*	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Participant))
			return false;
		Participant other = (Participant) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		return true;
	}*/
}
