package sportevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sportevent.dao.*;
import sportevent.model.Club;
import sportevent.model.Competition;
import sportevent.model.Participant;

import java.net.URI;
import java.util.List;

/**
 * REST Controller to manage clubs
 *
 * @author Birgit Reiter
 * @version 1.0
 *
 */
@RestController
@RequestMapping(path="/club")
public class ClubController {

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    /**
     * read all clubs from db
     *
     * @return list of clubs
     */
    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Club> readAllClubs(){
        return clubRepository.findAll();

    }

    /**
     * create an save new club
     *
     * @param name
     * @return url with new id
     */
    @RequestMapping(path = "{name}", method = RequestMethod.POST)
    public ResponseEntity<?> createClub(@PathVariable("name") String name){
        Club savedClub = clubRepository.save(new Club(name));
        if (savedClub!=null){
            // Build new path for event and return as new location
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().replacePath("/club/{id}")
                    .buildAndExpand(savedClub.getId()).toUri();
            return ResponseEntity.created(location).build();
        } else
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * update club informations
     *
     * @param id
     * @param updateClub
     */
    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public void updateClub(@PathVariable("id") Long id, @RequestBody Club updateClub){
        updateClub = clubRepository.save(updateClub);
    }

    @RequestMapping(path = "{id}/participants", method = RequestMethod.GET)
    public List<ParticipantWithCompetitions> getParticipantsForClub(@PathVariable("id") Long id) {
        //return competitionRepository.findByParticipantsClub_id(id);
        return participantRepository.findByClub_id(id);
    }
}
