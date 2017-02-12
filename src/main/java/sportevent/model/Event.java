package sportevent.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Events can be created by promoter and have one or more competitions
 *
 * @author Birgit Reiter
 * @version 1.0
 */

// use Lombock to reduce boilerplate
//@Data
@Getter
@Setter
@Entity
public class Event  implements Serializable {

	@Id
	@GeneratedValue
	// unique identifier
	private Long id;

	// date of event will take place
	private Date date;

	// official name
	private String name;

	// full description
	private String description;

	// image for event
	@Lob
	@JsonIgnore
	private byte[] image;

	@OneToMany(mappedBy = "event")
	@JsonIgnore
	// list of all possible competitions
	private List<Competition> competition;

	@ManyToOne
	@JoinColumn(name = "promoterid")
	//@JsonIgnore
	// promoter, who is responsible for event
	private Promoter promoter;

	public Event() {
	}

	public Event(Date date, String name, String description, Promoter promoter) {
		this.date = date;
		this.name = name;
		this.description = description;
		this.promoter = promoter;
	}
}
