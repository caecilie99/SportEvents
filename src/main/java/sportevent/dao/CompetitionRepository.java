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
    public List<Competition> findByEventId(Long event_id);

    public Competition findByIdAndParticipants_Id(Long id, Long pid);
    //public List<Competition> findByParticipantsClub_id(Long club_id);

    // TODO needed?
    //public List<Participant> findParticipantsByEventId(Long event_id);

}