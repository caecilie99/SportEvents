package sportevent.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sportevent.model.Club;

import java.util.List;

/**
 * interface for club repository
 *
 * @author Birgit Reiter
 * @version 1.0
 */
@Repository
public interface ClubRepository extends CrudRepository<Club, Long> {

    /**
     * find all clubs in repository
     * @return list of clubs
     */
    public List<Club> findAll();

    /**
     * find club by id
     *
     * @param id
     * @return club
     */
    public Club findById(Long id);

    /**
     * find club by name
     *
     * @param name
     * @return club
     */
    public Club findByName(String name);

    /**
     * find club by assigned user_id
     * @param userid
     * @return
     */
    public Club findByUser_id(Long userid);
}
