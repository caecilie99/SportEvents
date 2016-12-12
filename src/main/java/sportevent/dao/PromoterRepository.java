package sportevent.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sportevent.model.Promoter;

import java.util.List;

/**
 * Created by Birgit on 12.12.2016.
 */
@Repository
public interface PromoterRepository extends CrudRepository<Promoter, Long> {

    public List<Promoter> findAll();
    public Promoter findById(Long id);
    public Promoter findByName(String name);

}
