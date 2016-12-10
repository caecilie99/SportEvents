package sportevent.dao;

import org.springframework.data.repository.CrudRepository;
import sportevent.model.Contact;

import java.util.List;

/**
 * Created by Birgit on 10.12.2016.
 */
public interface ContactRepository extends CrudRepository<Contact, Long> {

    public List<Contact> findAll();
    public Contact findById(Long id);

}
