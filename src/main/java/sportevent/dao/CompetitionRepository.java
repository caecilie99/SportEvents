package sportevent.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sportevent.model.Club;
import sportevent.model.Competition;
import sportevent.model.Participant;

import java.util.List;

/**
 * interface for competition repository
 *
 * @author Birgit Reiter
 * @version 1.0
 */
@Repository
public interface CompetitionRepository extends CrudRepository<Competition, Long> {

    /**
     * find competitions for event by event id
     *
     * @param event_id
     * @return list of competitions
     */
    public List<CompetitionWithParticipants> findByEventId(Long event_id);

    // TODO needed?
    public List<Participant> findParticipantsByEventId(Long event_id);

}