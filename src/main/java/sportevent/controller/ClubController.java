package sportevent.controller;

import javafx.print.Collation;
import org.hibernate.mapping.Collection;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sportevent.dao.ClubDao;
import sportevent.model.Club;

/**
 * Created by Birgit on 21.11.2016.
 */
@RestController
@RequestMapping(path="/club")
public class ClubController {

    @Autowired
    private ClubDao clubDAO;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List readAllClubs(){
        return (List) clubDAO.findAll();

    }


    @RequestMapping(path = "{name}/create", method = RequestMethod.POST)
    public void createClub(@PathVariable("name") String name){
        Club newClub = new Club(name);
        clubDAO.save(newClub);
    }



}
