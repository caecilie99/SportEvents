package sportevent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Collection;

/**
 * User details, used for promoter and club
 *
 * @author Birgit Reiter
 * @version 1.0
 */

// use Lombock to reduce boilerplate
@Data
@Entity
public class User implements Serializable{

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

	public User() {
	}

	public User(String lastname, String firstname, String email, String username, String password) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.username = username;
		this.password = password;
	}
}
