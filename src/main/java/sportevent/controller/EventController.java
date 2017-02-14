package sportevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sportevent.dao.EventRepository;
import sportevent.dao.EventWithImage;
import sportevent.dao.PromoterRepository;
import sportevent.model.Event;
import sportevent.model.Promoter;

import java.net.URI;
import java.util.Date;
import java.util.List;

/**
 * REST controller to manage events
 *
 * @author Birgit Reiter
 * @version 1.0
 */
@RestController
@RequestMapping(path="/event")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PromoterRepository promoterRepository;

    /**
     * read all events from db and return as JSON
     *
     * @return list of events with images
     */
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<EventWithImage> getEvents(){
        return eventRepository.findAllProjectedBy();
    }

    /**
     * read all events from prometer from db and return as JSON
     *
     * @param promoterid id of promoter
     * @return list of events without images
     */
    @RequestMapping(path = "/{promoterid}/list", method = RequestMethod.GET)
    public List<Event> getEvents(@PathVariable Long promoterid){
         return eventRepository.findByPromoterId(promoterid);
    }

    /**
     * Find event by id and return event information
     *
     * @param id event id
     * @return event full event information with image
     *
     */
    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public EventWithImage getEvent(@PathVariable("id") Long id){
        EventWithImage test = eventRepository.findById(id);
        return test;
    }

    /**
     * create and save new event and return new location for event, identified by id
     * actually not used from client, but tested
     * image is missing
     *
     * @param name name of new event
     * @param description full description
     * @param date date for event
     * @param promoterId promoter id
     * @return url with new id
     */
    @RequestMapping(path = "{name}", method = RequestMethod.POST)
    public ResponseEntity<?> createEvent(@PathVariable("name") String name, @RequestParam("promoterid") Long promoterId, @RequestParam("description") String description, @RequestParam("date") String date){
        Promoter eventPromoter = promoterRepository.findById(promoterId);
        Event savedEvent = eventRepository.save(new Event(new Date(Long.parseLong(date)), name, description, eventPromoter));
        if (savedEvent!=null){
            // Build new path for event and return as new location
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().replacePath("/event/{id}")
                    .buildAndExpand(savedEvent.getId()).toUri();
            return ResponseEntity.created(location).build();
        } else
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * update event information
     * actually not used from client, but tested
     *
     * @param id id of event to be updated
     * @param event event object with new informations
     */
    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public void updateEvent(@PathVariable("id") Long id, @RequestBody Event event){
        eventRepository.save(event);
    }

}