package sportevent.dao;

import javax.persistence.Lob;
import java.util.Date;

/**
 * Created by Birgit on 12.02.2017.
 *
 * used for full information for event with image
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
