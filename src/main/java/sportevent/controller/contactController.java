package sportevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sportevent.dao.ContactRepository;
import sportevent.model.Club;
import sportevent.model.Contact;

import java.net.URI;
import java.util.List;

/**
 * Created by Birgit on 11.12.2016.
 */
@RestController
@RequestMapping(path="/club/{id}/contact")
public class contactController {

    @Autowired
    private ContactRepository contactRepository;

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<?> createClub(@RequestBody Contact newContact){
        Contact savedContact = contactRepository.save(newContact);
        if (savedContact!=null){
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedContact.getId()).toUri();
            return ResponseEntity.created(location).build();
        } else
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public void createClub(@PathVariable("id") Long id, @RequestBody Contact updateContact){
        updateContact = contactRepository.save(updateContact);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Contact createClub(@PathVariable("id") Long id){
        return contactRepository.findById(id);
    }
}
