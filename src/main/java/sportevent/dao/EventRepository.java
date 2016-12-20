package sportevent.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sportevent.model.Club;
import sportevent.model.Event;
import sportevent.model.Promoter;

import java.util.List;

/**
 * Repository for events
 *
 * @author Birgit Reiter
 * @version 1.0
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    public List<Event> findAll();
    public List<Event> findByPromoterId(Long promoterid);
    public Event findById(Long id);
    public Event findByName(String name);

}