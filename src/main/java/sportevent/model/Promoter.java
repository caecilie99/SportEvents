package sportevent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Promoter can create and manage events
 *
 * @author Birgit Reiter
 * @version 1.0
 */

// use Lombock to create getter and setter to reduce boilerplate
//@Data
@Getter
@Setter
@Entity
public class Promoter implements Serializable {

	@Id
	@GeneratedValue
	// unique identifier
	private Long id;

	@Column(nullable = false)
	// official name
	private String name;

	// full description
	private String description;

	@Column(nullable = false)
	// url for more information
	private String url;

	@OneToMany(mappedBy = "promoter")
	@JsonIgnore
	// assigned events
	private List<Event> event;

	@OneToOne
	@JoinColumn(name = "user_id")
	//@JsonIgnore
	// user information for sign in
	private User user;

	public Promoter() {
	}

	public Promoter(String name, String description, String url) {
		this.name = name;
		this.description = description;
		this.url = url;
	}
}
