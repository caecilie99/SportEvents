package sportevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sportevent.dao.EventRepository;
import sportevent.dao.PromoterRepository;
import sportevent.model.Event;
import sportevent.model.Promoter;

import java.net.URI;
import java.util.Date;
import java.util.List;

/**
 * REST controller for promoter
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

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Event> getEvents(@RequestParam(value = "promoterid", required = false) Long promoterid){
        if (promoterid!=null)
            return eventRepository.findByPromoterId(promoterid);
        else
            return eventRepository.findAll();

    }

    /**
     * create new event and return new location for event, identified by id
     *
     * @param name
     * @param description
     * @param date
     * @param promoterId
     * @return response result
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
     *
     * @param id
     * @return event
     *
     */
    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Event getEvent(@PathVariable("id") Long id){
        return eventRepository.findById(id);
    }

    /**
     * save event
     *
     * @param id
     * @param promoterEvent
     */
    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public void updateEvent(@PathVariable("id") Long id, @RequestBody Event promoterEvent){
        promoterEvent = eventRepository.save(promoterEvent);
    }

}