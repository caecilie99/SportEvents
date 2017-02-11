package sportevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sportevent.dao.ClubRepository;
import sportevent.dao.UserRepository;
import sportevent.model.Club;
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
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClubRepository clubRepository;

    /**
     * create and save new user
     *
     * @param username
     * @param firstname
     * @param lastname
     * @param email
     * @param password
     * @return url with new id
     */
    @RequestMapping(path="{username}", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@PathVariable("username") String username, @RequestParam String firstname,
                                        @RequestParam String lastname, @RequestParam String email, @RequestParam String password){
        User newUser = new User(lastname, firstname, email, username, password);
        userRepository.save(newUser);
        if (newUser !=null){
            // Append new id to request path and return as new location
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newUser.getId()).toUri();
            return ResponseEntity.created(location).build();
        } else
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * update user information
     *
     * @param id
     * @param firstname
     * @param lastname
     * @param email
     */
    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?>  updateUser(@PathVariable("id") Long id, @RequestParam(value = "firstname", required = false) String firstname,
                           @RequestParam(value = "lastname", required = false) String lastname,
                                         @RequestParam(value = "email", required = false) String email,
                                         @RequestParam(value = "zipcode", required = false) String zipcode,
                                         @RequestParam(value = "city", required = false) String city,
                                         @RequestParam(value = "adress", required = false) String adress,
                                         @RequestParam(value = "phone", required = false) String phone){
        User updateUser = userRepository.findById(id);
        if (updateUser !=null){
            if (firstname!=null) updateUser.setFirstname(firstname);
            if (lastname!=null) updateUser.setLastname(lastname);
            if (email!=null) updateUser.setEmail(email);
            if (adress!=null) updateUser.setAdress(adress);
            if (zipcode!=null) updateUser.setZipcode(zipcode);
            if (city!=null) updateUser.setCity(city);
            if (phone!=null) updateUser.setPhone(phone);
            userRepository.save(updateUser);
            return ResponseEntity.accepted().build();
        } else {
            return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * find user by username
     *
     * @param name
     * @return
     */
    @RequestMapping(path = "{name}", method = RequestMethod.GET)
    public User findUser(@PathVariable("name") String name){
        return userRepository.findByUsername(name);
    }

    /**
     * find user by username
     *
     * @param name
     * @return
     */
    @RequestMapping(path = "{name}/club", method = RequestMethod.GET)
    public Club findClubByUser(@PathVariable("name") String name){
        User user = userRepository.findByUsername(name);
        if (user!=null) {
            return clubRepository.findByUser_id(user.getId());
        } else
            return null;
    }
}
