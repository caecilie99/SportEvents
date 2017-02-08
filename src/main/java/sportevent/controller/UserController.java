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
 * REST controller to user information
 *
 * @author Birgit Reiter
 * @version 1.0
 */

// TODO add user for promoter
@RestController
@RequestMapping(path="/club/{id}/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * create and save new user
     *
     * @param newUser
     * @return
     */
    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User newUser){
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

    /**
     * update user information
     *
     * @param id
     * @param updateUser
     */
    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public void updateUser(@PathVariable("id") Long id, @RequestBody User updateUser){
        updateUser = userRepository.save(updateUser);
    }

    /**
     * find user by id
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public User findUser(@PathVariable("id") Long id){
        return userRepository.findById(id);
    }
}
