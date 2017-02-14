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
     * find all participants
     * @return list of participants
     */
    public List<Participant> findAll();

    /**
     * find participannt by id

     * @param id participant id
     * @return pariticipant
     */
    public Participant findById(Long id);

    /**
     * find participants for club
     *
     * @param clubid club id
     * @return list of participants assigned to club
     */
    public List<ParticipantWithCompetitions> findByClub_id(Long clubid);

    /**
     * find participant with firstname, lastname and year
     *
     * @param last lastname
     * @param first firstname
     * @param year year
     * @return participant
     */
    public Participant findByLastnameAndFirstnameAndYear(String last, String first, Long year);

}