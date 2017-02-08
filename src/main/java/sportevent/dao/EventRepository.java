package sportevent.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sportevent.model.Club;
import sportevent.model.Event;
import sportevent.model.Promoter;

import java.util.List;

/**
 * interface for repository for events
 *
 * @author Birgit Reiter
 * @version 1.0
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    /**
     * find all events in repository
     * @return list of events
     */
    public List<Event> findAll();

    /**
     * find events from promoter
     *
     * @param promoterid
     * @return list of events
     */
    public List<Event> findByPromoterId(@Param("promoterid") Long promoterid);

    /**
     * find event by id
     * @param id
     * @return event
     */
    public Event findById(@Param("id") Long id);

    /**
     * find event by name
     * @param name
     * @return
     */
    public Event findByName(@Param("name") String name);

}