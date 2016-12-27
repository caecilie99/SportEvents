package sportevent.model;

import lombok.Data;

import javax.persistence.*;

/**
 * AgeGroups are definded by fromYear an toYear and could be assigned to competitions
 *
 * @author Birgit Reiter
 * @version 1.0
 */

// use Lombock to reduce boilerplate
@Data
@Entity
public class AgeGroup {

	@Id
	@GeneratedValue
	private int id;

	@Column(nullable = false)
	private String shorcut;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private int fromYear;

	@Column(nullable = false)
	private int toYear;

	public AgeGroup() {
	}

	public AgeGroup(String shorcut, String name, int fromYear, int toYear) {
		this.shorcut = shorcut;
		this.name = name;
		this.fromYear = fromYear;
		this.toYear = toYear;
	}

}
