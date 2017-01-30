package sportevent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * Participants can attend to a competition
 *
 * @author Birgit Reiter
 * @version 1.0
 */

// use Lombock to reduce boilerplate
//@Data
@Getter
@Setter
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

    @ManyToOne(/*cascade = CascadeType.ALL, fetch = FetchType.LAZY*/)
    @JoinColumn(name="club_id", updatable = false)
    //@JsonIgnore
	private Club club;

	@NotEmpty
	@ManyToMany(/*cascade = CascadeType.ALL, fetch = FetchType.LAZY*/)
	@JoinTable(	joinColumns = {@JoinColumn(name = "participant_id", referencedColumnName = "participant_id")},
			inverseJoinColumns = {@JoinColumn(name = "competition_id", referencedColumnName = "competition_id")})
    @JsonIgnore
	private Set<Competition> competition = new HashSet<Competition>();

    @OneToOne
    @JoinColumn(name="agegroup_id")
    //@JsonIgnore
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
}
