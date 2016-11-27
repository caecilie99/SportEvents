package sportevent.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Promoter{

	@Id
	@GeneratedValue
	private int id;

	@Column(nullable = false)
	private String name;

	private String description;

	@Column(nullable = false)
	private String url;

	@OneToMany(mappedBy = "promoter")
	private List<Event> event;

	@OneToMany
	private List<Contact> contact;

	public Promoter() {
	}

	public Promoter(String name, String description, String url) {
		this.name = name;
		this.description = description;
		this.url = url;
	}
}
