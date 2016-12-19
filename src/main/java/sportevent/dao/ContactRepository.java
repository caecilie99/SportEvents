package sportevent.dao;

import org.springframework.data.repository.CrudRepository;
import sportevent.model.Contact;

import java.util.List;

/**
 * Repository for contacts
 *
 * @author Birgit Reiter
 * @version 1.0
 */
public interface ContactRepository extends CrudRepository<Contact, Long> {

    public List<Contact> findAll();
    public Contact findById(Long id);

}
