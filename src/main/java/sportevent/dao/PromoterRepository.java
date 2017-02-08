package sportevent.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sportevent.model.Promoter;

import java.util.List;

/**
 * interface for repository for events
 *
 * @author Birgit Reiter
 * @version 1.0
 */

@Repository
public interface PromoterRepository extends CrudRepository<Promoter, Long> {

    /**
     * find all promoter in repository
     * @return list of promoter
     */
    public List<Promoter> findAll();

    /**
     * find promoter by id
     * @param id
     * @return promoter
     */
    public Promoter findById(Long id);

    /**
     * TODO needed?
     * find promoter by name
     * @param name
     * @return
     */
    public Promoter findByName(String name);

}
