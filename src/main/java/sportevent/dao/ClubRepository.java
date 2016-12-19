package sportevent.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sportevent.model.Club;

import java.util.List;

/**
 * Repository for clubs
 *
 * @author Birgit Reiter
 * @version 1.0
 */
@Repository
public interface ClubRepository extends CrudRepository<Club, Long> {

    public List<Club> findAll();
    public Club findById(Long id);
    public Club findByName(String name);

}
