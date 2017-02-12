package sportevent.dao;

import javax.persistence.Lob;
import java.util.Date;

/**
 * Created by Birgit on 12.02.2017.
 */
public interface EventWithImage {
    Long getId();

    // date of event will take place
    Date getDate();

    // official name
    String getName();

    // full description
    String getDescription();

    // image for event
    byte[] getImage();
}
