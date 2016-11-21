package sportevent.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sportevent.model.Club;

import java.util.List;

/**
 * Created by Birgit on 21.11.2016.
 */
@Repository
public interface ClubDao extends CrudRepository<Club, Long> {

    public List<Club> findAll();
    public Club findById(Long id);
    public Club findByName(String name);

}
