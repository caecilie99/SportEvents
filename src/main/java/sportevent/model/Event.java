package sportevent.model;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity

public class Event {

	@Id
	@GeneratedValue
	private int id;

	private Date date;

	private String name;

	private String description;

	@OneToMany(mappedBy = "event")
	private List<Competition> competition;

	@ManyToOne
	@JoinColumn(name = "Id")
	private Promoter club;

}
