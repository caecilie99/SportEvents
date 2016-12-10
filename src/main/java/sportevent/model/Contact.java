package sportevent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Contact implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

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

	public Contact() {
	}

	public Contact(String lastname, String firstname, String email, String username, String password) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.username = username;
		this.password = password;
	}
}
