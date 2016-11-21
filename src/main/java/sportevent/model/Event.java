package sportevent.model;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

public class Event {

	private int id;

	private Date date;

	private String name;

	private String description;

	private Competition[] competition;

	@ManyToOne
	@JoinColumn(name = "club_id")
	private Club club;

}
