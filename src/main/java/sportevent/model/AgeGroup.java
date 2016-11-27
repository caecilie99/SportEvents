package sportevent.model;

import javax.persistence.*;

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

	public String getShorcut() {
		return shorcut;
	}

	public void setShorcut(String shorcut) {
		this.shorcut = shorcut;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFromYear() {
		return fromYear;
	}

	public void setFromYear(int fromYear) {
		this.fromYear = fromYear;
	}

	public int getToYear() {
		return toYear;
	}

	public void setToYear(int toYear) {
		this.toYear = toYear;
	}
}
