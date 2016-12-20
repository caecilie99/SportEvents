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
import sportevent.model.Promoter;

import java.net.URI;
import java.util.Date;
import java.util.List;

/**
 * REST controller for competitions
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
     * find all competitions for one event
     *
     * @return list of events
     */
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Competition> getCompetitions(@PathVariable("eventid") Long eventId){
        return competitionRepository.findByEventId(eventId);
    }

    /**
     * create new competition and return new location for competition, identified by id
     *
     * @param name
     * @param description
     * @param eventId
     * @return response result
     */
    @RequestMapping(path = "{name}", method = RequestMethod.POST)
    public ResponseEntity<?> createEvent(@PathVariable("name") String name, @PathVariable("eventid") Long eventId, @RequestParam("description") String description, @RequestParam("fee") Double fee){
        Event event = eventRepository.findById(eventId);
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
