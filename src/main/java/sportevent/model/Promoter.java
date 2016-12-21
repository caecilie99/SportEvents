package sportevent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Promoter can create and manage events
 *
 * @author Birgit Reiter
 * @version 1.0
 */

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

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
