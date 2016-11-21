package sportevent.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Club {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String description;

	private String url;

	@OneToMany(mappedBy = "club")
	private Event[] event;

	@OneToMany(mappedBy = "club")
	private Contact[] contact;

	public Club() {
		this.name = "";
	}

	public Club(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
