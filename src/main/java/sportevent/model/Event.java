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
@Data
@Entity
public class Event  implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private Date date;

	private String name;

	private String description;

	@OneToMany(mappedBy = "event")
	@JsonIgnore
	private List<Competition> competition;

	@ManyToOne
	@JoinColumn(name = "promoter_id")
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
