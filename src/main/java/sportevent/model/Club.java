package sportevent.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Club {

    @Id
    @GeneratedValue
	private int id;


    @Column(nullable = false)
	private String name;

    @OneToMany(mappedBy = "Club")
	private List<Participant> participant;

    @OneToOne
    @JoinColumn(name="Id")
	private Contact contact;

}
