package sportevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sportevent.dao.UserRepository;
import sportevent.model.User;

import java.net.URI;

/**
 * Created by Birgit on 11.12.2016.
 */
@RestController
@RequestMapping(path="/club/{id}/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<?> createClub(@RequestBody User newUser){
        User savedUser = userRepository.save(newUser);
        if (savedUser !=null){
            // Append new id to request path and return as new location
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedUser.getId()).toUri();
            return ResponseEntity.created(location).build();
        } else
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public void createClub(@PathVariable("id") Long id, @RequestBody User updateUser){
        updateUser = userRepository.save(updateUser);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public User createClub(@PathVariable("id") Long id){
        return userRepository.findById(id);
    }
}
