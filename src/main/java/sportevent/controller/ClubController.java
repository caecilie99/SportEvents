package sportevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sportevent.dao.ClubRepository;
import sportevent.model.Club;

import java.net.URI;
import java.util.List;

/**
 * Created by Birgit on 21.11.2016.
 */
@RestController
@RequestMapping(path="/club")
public class ClubController {

    @Autowired
    private ClubRepository clubRepository;

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Club> readAllClubs(){
        return clubRepository.findAll();

    }

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

    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public void updateClub(@PathVariable("id") Long id, @RequestBody Club updateClub){
        updateClub = clubRepository.save(updateClub);
    }

}
