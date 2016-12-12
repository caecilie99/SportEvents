package sportevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sportevent.dao.PromoterRepository;
import sportevent.model.Promoter;

import java.net.URI;
import java.util.List;

/**
 * Created by Birgit on 21.11.2016.
 */
@RestController
@RequestMapping(path="/promoter")
public class PromoterController {

    @Autowired
    private PromoterRepository promoterRepository;

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Promoter> readAllClubs(){
        return promoterRepository.findAll();

    }

    @RequestMapping(path = "{name}", method = RequestMethod.POST)
    public ResponseEntity<?> createPromoter(@PathVariable("name") String name, @RequestParam("description") String description, @RequestParam("url") String url){
        Promoter savedPromoter = promoterRepository.save(new Promoter(name, description, url));
        if (savedPromoter!=null){
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedPromoter.getId()).toUri();
            return ResponseEntity.created(location).build();
        } else
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public void updateClub(@PathVariable("id") Long id, @RequestBody Promoter promoterClub){
        promoterClub = promoterRepository.save(promoterClub);
    }

}
