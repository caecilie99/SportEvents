package sportevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sportevent.dao.ClubRepository;
import sportevent.dao.CompetitionRepository;
import sportevent.dao.EventRepository;
import sportevent.dao.ParticipantRepository;
import sportevent.model.Club;
import sportevent.model.Competition;
import sportevent.model.Participant;

import java.net.URI;
import java.util.List;
import java.util.Set;

/**
 * REST controller to manage participants
 *
 * @author Birgit Reiter
 * @version 1.0
 */
@RestController
public class ParticipantController {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    /**
     * add participant to competetion for event
     *
     * @param clubId
     * @param eventId
     * @param lastName
     * @param firstName
     * @param year
     * @return response result
     */
    @RequestMapping(path = "/event/{eventid}/participants/{clubid}/add", method = RequestMethod.POST)
    public ResponseEntity<?> addParticipant(@PathVariable("eventid") Long eventId, @PathVariable("clubid") Long clubId,
                                            @RequestParam("lastname") String lastName, @RequestParam("firstname") String firstName,
                                            @RequestParam("year") Long year, @RequestParam("competition") Set<Competition> competitions) {
        Club club = clubRepository.findById(clubId);
        Participant savedParticipant = participantRepository.save(new Participant(lastName, firstName, year, club, competitions));
        if (savedParticipant != null) {
            // Build new path for event and return as new location
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().replacePath("/event/{eventid}/participants/{clubid}/{id}")
                    .buildAndExpand(eventId, clubId, savedParticipant.getId()).toUri();
            return ResponseEntity.created(location).build();
        } else
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(path = "/participant/{id}/delete", method = RequestMethod.DELETE)
    public void deleteCompetitionForParticipant(@PathVariable("id") Long id) {
        Participant part = participantRepository.findOne(id);
        participantRepository.delete(part);

    }

}
