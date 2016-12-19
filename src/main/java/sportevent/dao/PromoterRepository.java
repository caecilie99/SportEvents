package sportevent.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sportevent.model.Promoter;

import java.util.List;

/**
 * Repository for events
 *
 * @author Birgit Reiter
 * @version 1.0
 */

@Repository
public interface PromoterRepository extends CrudRepository<Promoter, Long> {

    public List<Promoter> findAll();
    public Promoter findById(Long id);
    public Promoter findByName(String name);

}
