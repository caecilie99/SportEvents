package sportevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sportevent.dao.CompetitionRepository;
import sportevent.dao.EventRepository;
import sportevent.model.Competition;
import sportevent.model.Event;

import java.net.URI;
import java.util.List;

/**
 * REST controller to manage competitions for events
 * Each event can have one or more competitions
 *
 * @author Birgit Reiter
 * @version 1.0
 */
@RestController
@RequestMapping(path="/event/{eventid}/competition")
public class CompetitionController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    /**
     * find all competitions for event
     *
     * @param eventId id for event
     * @return list of competitions
     */
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Competition> getCompetitionse(@PathVariable("eventid") Long eventId){
        List<Competition> comp =competitionRepository.findByEventId(eventId);
        return comp;
    }

    /**
     * create and save new competition, return new location for competition, identified by id
     *
     * @param name name of new competition
     * @param description full description of new description
     * @param eventId evend id of assigned event
     * @param fee fee for competition
     * @return url with new id
     */
    @RequestMapping(path = "{name}", method = RequestMethod.POST)
    public ResponseEntity<?> createNewCompetition(@PathVariable("name") String name, @PathVariable("eventid") Long eventId, @RequestParam("description") String description, @RequestParam("fee") Double fee){
        Event event = eventRepository.findOne(eventId);
        Competition savedCompetition = competitionRepository.save(new Competition(name, description, fee, event));
        if (savedCompetition!=null){
            // Build new path for event and return as new location
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().replacePath("/event/{eventid}/competition/{id}")
                    .buildAndExpand(event.getId(), savedCompetition.getId()).toUri();
            return ResponseEntity.created(location).build();
        } else
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
