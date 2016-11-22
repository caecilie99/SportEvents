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

	@OneToMany(mappedBy = "promoter")
	private List<Contact> contact;

}
