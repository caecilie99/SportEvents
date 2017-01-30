package sportevent.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sportevent.model.Club;
import sportevent.model.Competition;
import sportevent.model.Participant;

import java.util.List;

/**
 * Repository for competitions
 *
 * @author Birgit Reiter
 * @version 1.0
 */
@Repository
public interface CompetitionRepository extends CrudRepository<Competition, Long> {

    public List<Competition> findAll();
    public Competition findById(Long id);
    public Competition findByName(String name);
    public List<Competition> findByEventId(Long event_id);
    public List<Participant> findParticipantsByEventId(Long event_id);

}