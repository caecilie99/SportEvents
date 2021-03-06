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
import java.util.HashSet;
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

    @Autowired
    private CompetitionRepository competitionRepository;

    /**
     * add participant to competetion for event
     *
     * @param eventId id of event
     * @param compId id of competition
     * @param clubId id of club for participant
     * @param participant participant object with infos like firstname, lastname, year
     * @return ResponseEntitiy
     */
    @RequestMapping(path = "/event/{eventid}/{compid}/participants/{clubid}/add", method = RequestMethod.POST)
    public ResponseEntity<?> addParticipant(@PathVariable("eventid") Long eventId, @PathVariable("clubid") Long clubId,
                                            @PathVariable("compid") Long compId,
                                            @RequestBody Participant participant) {
        // find club for participant
        Club club = clubRepository.findOne(clubId);

        // check if participant exists
        // first- and lastname and year unique, should be extended with club_id, but doesn't work
        Participant partExist = participantRepository.findByLastnameAndFirstnameAndYear(participant.getLastname(), participant.getFirstname(), participant.getYear());

        if (partExist!=null)
            participant = partExist;
        else
            participant.setClub(club);

        // add competition to participant
        Competition competition = competitionRepository.findOne(compId);
        participant.getCompetition().add(competition);

        // save participant
        participantRepository.save(participant);

        if (participant != null) {
            // response with participant in body and the status set to OK
            return ResponseEntity.ok(participant);
        } else
            // error
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     * Delete complete participant
     * actually nbot used from client
     *
     * @param id id of participant
     */
    @RequestMapping(path = "/participant/{id}", method = RequestMethod.DELETE)
    public void deleteParticipant(@PathVariable("id") Long id) {
        Participant part = participantRepository.findOne(id);
        //Competition test = competitionRepository.findByIdAndParticipants_Id(id, pid);
        participantRepository.delete(part);
    }

    /**
     * Delete competition for participant

     * @param id id of participant
     * @param compid id of competition to be deleted
     */
    @RequestMapping(path = "/participant/{id}/{compid}", method = RequestMethod.DELETE)
    public void deleteCompetitionForParticipant(@PathVariable("id") Long id, @PathVariable("compid") Long compid) {
        // get participant
        Participant part = participantRepository.findOne(id);
        // get full competition
        Competition comp = competitionRepository.findOne(compid);
        // remove competiton from participant
        part.getCompetition().remove(comp);
        // save participant
        participantRepository.save(part);
    }

}
