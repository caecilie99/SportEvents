package sportevent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

// use Lombock to create getter and setter to reduce boilerplate
//@Data
@Getter
@Setter
@Entity
public class User implements Serializable{
        public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

        @Id
        @GeneratedValue
        // unique identifier
        private Long id;

        @Column(nullable = false)
        // lastname of responsible person
        private String lastname;

        @Column(nullable = false)
        // firstname of responsible person
        private String firstname;

        // adress of responsible person
        private String adress;

        // zipcode
        private String zipcode;

        // city
        private String city;

        @Column(nullable = false)
        // email for contact
        private String email;

        // pone number for contact
        private String phone;

        @Column(nullable = false, unique = true)
        // username to sign in
        private String username;

        @Column(nullable = false)
        @JsonIgnore
        // password
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

        public void setPassword(String password) {
            // use bcrypt to set password
            this.password = password;//PASSWORD_ENCODER.encode(password);
        }
}
