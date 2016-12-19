package sportevent.model;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Events can be created by promoter and have one or more competitions
 *
 * @author Birgit Reiter
 * @version 1.0
 */

@Entity
public class Event {

	@Id
	@GeneratedValue
	private Long id;

	private Date date;

	private String name;

	private String description;

	@OneToMany(mappedBy = "event")
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

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public List<Competition> getCompetition() {
		return competition;
	}

	public void setCompetition(List<Competition> competition) {
		this.competition = competition;
	}

	public Promoter getPromoter() {
		return promoter;
	}

	public void setPromoter(Promoter promoter) {
		this.promoter = promoter;
	}
}
