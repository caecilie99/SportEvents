package sportevent.dao;

import org.springframework.data.repository.CrudRepository;
import sportevent.model.User;

import java.util.List;

/**
 * Repository for contacts
 *
 * @author Birgit Reiter
 * @version 1.0
 */
public interface UserRepository extends CrudRepository<User, Long> {

    public List<User> findAll();
    public User findById(Long id);
    public User findByUsername(String username);

}
