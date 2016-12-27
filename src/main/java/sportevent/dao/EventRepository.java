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
 * Repository for events
 *
 * @author Birgit Reiter
 * @version 1.0
 */
//@Repository
@RepositoryRestResource(collectionResourceRel = "events", path = "events")
public interface EventRepository extends CrudRepository<Event, Long> {

    public List<Event> findAll();
    public List<Event> findByPromoterId(@Param("promoterid") Long promoterid);
    public Event findById(@Param("id") Long id);
    public Event findByName(@Param("name") String name);

}