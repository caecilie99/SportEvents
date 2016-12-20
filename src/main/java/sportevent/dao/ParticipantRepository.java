package sportevent.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sportevent.model.Participant;

import java.util.List;

/**
 * Repository for competitions
 *
 * @author Birgit Reiter
 * @version 1.0
 */
@Repository
public interface ParticipantRepository extends CrudRepository<Participant, Long> {

    public List<Participant> findAll();
    public Participant findById(Long id);
    //public Participant findByName(String name);
    //public List<Participant> findByEventId(Long event_id);

}