package sportevent.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.tags.form.SelectTag;
import sportevent.model.Competition;
import sportevent.model.Participant;
import java.util.List;
import java.util.Set;

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

    public List<ParticipantWithCompetitions> findByClub_id(Long clubid);

    public Participant findByIdAndCompetition_Id(Long id, Long compId);

}