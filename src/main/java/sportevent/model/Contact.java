package sportevent.model;

import javax.persistence.*;

@Entity
public class Contact {

	@Id
	@GeneratedValue
	private int id;

	private String lastname;

	private String firstname;

	private String adress;

	private String zipcode;

	private String city;

	private String email;

	private String phone;

	@ManyToOne
	@JoinColumn(name = "club_id")
	private Club club;

}
