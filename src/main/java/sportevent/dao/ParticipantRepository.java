package sportevent.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sportevent.model.Participant;

import java.util.List;

/**
 * interface repository for competitions
 *
 * @author Birgit Reiter
 * @version 1.0
 */
@Repository
public interface ParticipantRepository extends CrudRepository<Participant, Long> {

    /**
     * TODO needed?
     *
     * find all participants
     * @return list of participants
     */
    public List<Participant> findAll();

    /**
     * TODO needed
     * find participannt by id
     * @param id
     * @return pariticipant
     */
    public Participant findById(Long id);
    //public Participant findByName(String name);

}