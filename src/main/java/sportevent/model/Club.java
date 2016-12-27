package sportevent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Clubs can sign in participants for events from promoter clubs
 *
 * @author Birgit Reiter
 * @version 1.0
 */

// use Lombock to reduce boilerplate
@Data
@Entity
public class Club implements Serializable {

    @Id
    @GeneratedValue
	private Long id;


    @Column(nullable = false)
	private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "club", fetch=FetchType.EAGER)
	private List<Participant> participant;

    @OneToOne
    @JoinColumn(name = "contact_id")
    @JsonIgnore
	private User user;

    public Club() {
        this.name = "";
        //this.participant = new ArrayList<Participant>();
        //this.participant.add(new Participant());
        //this.user = new User();
    }

    public Club(String name) {
        this.name = name;
    }

}
