package sportevent.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Club {

    @Id
    @GeneratedValue
	private int id;


    @Column(nullable = false)
	private String name;

    @OneToMany(mappedBy = "club")
	private List<Participant> participant;

    @OneToOne
	private Contact contact;

    public Club() {
        this.name = "";
        this.participant = new ArrayList<Participant>();
        this.contact = new Contact();
    }

    public Club(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Participant> getParticipant() {
        return participant;
    }

    public void setParticipant(List<Participant> participant) {
        this.participant = participant;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
