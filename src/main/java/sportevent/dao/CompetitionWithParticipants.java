package sportevent.dao;

import sportevent.model.Participant;

import javax.persistence.Column;
import java.util.Set;

/**
 * Created by Birgit on 12.02.2017.
 */
public interface CompetitionWithParticipants {
    Long getId();

    // short name
    String getName();

    // full description
    String getDescription();

    // fee to pay
    Double getFee();

    // list of participants
    Set<Participant> getParticipants();
}
