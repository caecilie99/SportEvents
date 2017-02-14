package sportevent.dao;

import org.springframework.data.repository.CrudRepository;
import sportevent.model.User;

import java.util.List;

/**
 * interface repository for contacts
 *
 * @author Birgit Reiter
 * @version 1.0
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * find user by id
     *
     * @param id user id
     * @return user
     */
    public User findById(Long id);

    /**
     * find user by name
     *
     * @param username username
     * @return user
     */
    public User findByUsername(String username);

}
