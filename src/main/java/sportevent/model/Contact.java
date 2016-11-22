package sportevent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Contact {

	@Id
	@GeneratedValue
	private int id;

	@Column(nullable = false)
	private String lastname;

	@Column(nullable = false)
	private String firstname;

	private String adress;

	private String zipcode;

	private String city;

	@Column(nullable = false)
	private String email;

	private String phone;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

}
