package sportevent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import sportevent.dao.*;
import sportevent.model.Club;
import sportevent.model.Competition;
import sportevent.model.Event;
import sportevent.model.Promoter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Birgit on 20.12.2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParticipantControllerRESTTest {

    private Promoter testPromoter1Obj;
    private Club club1Obj;

    private Event event1Obj;
    private Event event2Obj;

    private Competition competition1Obj;
    private Competition competition2Obj;
    private Competition competition3Obj;
    private Competition competition4Obj;

    private static final String club1 = "Die Wellness-Oase";
    private static final String club2 = "FC Stramme Waden";

    private static final String event1 = "Lange Saunanacht";
    private static final String event2 = "24-h Lauf";

    private static final String competiton1 = "100 Grad Sauna";
    private static final String description1 = "Sauna bis der Arzt kommt";
    private static final Double fee1 = 9.0;


    private static final String competiton2 = "60 Grad Dampf Sauna";
    private static final String description2 = "Wellness für die Seele";
    private static final Double fee2 = 6.0;


    private static final String competiton3 = "auf dem Sportplatz";
    private static final String description3 = "Drehwurm garantiert";
    private static final Double fee3 = 9.0;

    private static final String competiton4 = "rund um Hösseringen";
    private static final String description4 = "Landschaft bis zum Abwinken";
    private static final Double fee4 = 25.0;

    private static final String lastName1 = "Blümchen";
    private static final String firstName1 = "Benjamin";
    private static final long year1 = 1988;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private PromoterRepository promoterRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private ParticipantRepository participantRepositiory;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        //MockitoAnnotations.initMocks(this);
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        // start with empty tables, order is important
        this.participantRepositiory.deleteAll();
        this.competitionRepository.deleteAll();
        this.eventRepository.deleteAll();
        this.promoterRepository.deleteAll();
        this.clubRepository.deleteAll();

        // create promoter to use for tests
        testPromoter1Obj = promoterRepository.save(new Promoter(club1, "desricption of "+club1, "www.urlclub1.de"));
        assertNotNull(testPromoter1Obj);

        // create promoter to use for tests
        club1Obj = clubRepository.save(new Club(club2));
        assertNotNull(club1Obj);

        // create and save two events for promoter1
        event1Obj = new Event(new Date(), event1, "some stuff", testPromoter1Obj);
        assertNotNull(eventRepository.save(event1Obj));

        event2Obj = new Event(new Date(), event2, "some hot stuff", testPromoter1Obj);
        assertNotNull(eventRepository.save(event2Obj));

        // create and save two competitions for event1
        competition1Obj = competitionRepository.save(new Competition(competiton1, description1, fee1, event1Obj));
        assertNotNull(competition1Obj);

        competition2Obj = competitionRepository.save(new Competition(competiton2, description2, fee2, event1Obj));
        assertNotNull(competition2Obj);

        // create and save two competitions for event2
        competition3Obj = competitionRepository.save(new Competition(competiton3, description3, fee3, event2Obj));
        assertNotNull(competition3Obj);

        competition4Obj = competitionRepository.save(new Competition(competiton4, description4, fee4, event2Obj));
        assertNotNull(competition4Obj);
    }

    @Test
    public void addParticipantWithCompetitions() throws Exception {

        this.mockMvc.perform(post("/event/{eventid}/participants/{clubid}/add", event1Obj.getId(), club1Obj.getId())
                .param("lastname", lastName1)
                .param("firstname", firstName1)
                .param("year", Long.toString(year1))
                .param("competition", competition1Obj.getId().toString()))
                .andExpect(status().isCreated());

    }

}

