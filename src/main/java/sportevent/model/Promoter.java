package sportevent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Promoter can create and manage events
 *
 * @author Birgit Reiter
 * @version 1.0
 */

// use Lombock to reduce boilerplate
@Data
@Entity
public class Promoter implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	private String description;

	@Column(nullable = false)
	private String url;

	@OneToMany(mappedBy = "promoter")
	@JsonIgnore
	private List<Event> event;

	@OneToOne
	@JoinColumn(name = "contact_id")
	@JsonIgnore
	private User user;

	public Promoter() {
	}

	public Promoter(String name, String description, String url) {
		this.name = name;
		this.description = description;
		this.url = url;
	}
}
