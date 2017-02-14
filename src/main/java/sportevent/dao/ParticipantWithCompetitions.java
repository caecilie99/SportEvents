package sportevent.dao;

import sportevent.model.Competition;

import java.util.Set;

/**
 * Created by Birgit on 12.02.2017.
 *
 * projection used in clubcontroller to get participant with competitions
 */
public interface ParticipantWithCompetitions {
    Long getId();

    // lastname
    String getLastname();

    // firstname
    String getFirstname();

    // year of birth
    Long getYear();

    // assigned competitons
    Set<Competition> getCompetition();
}
