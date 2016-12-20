package sportevent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import sportevent.dao.CompetitionRepository;
import sportevent.dao.EventRepository;
import sportevent.dao.PromoterRepository;
import sportevent.model.Competition;
import sportevent.model.Event;
import sportevent.model.Promoter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Birgit on 20.12.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class CompetitionControllerRESTTest {

    private Promoter testPromoter1Obj;
    private Promoter testPromoter2Obj;

    private Event event1Obj;
    private Event event2Obj;

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
        this.competitionRepository.deleteAll();
        this.eventRepository.deleteAll();
        this.promoterRepository.deleteAll();

        // create clubs to use for tests
        testPromoter1Obj = promoterRepository.save(new Promoter(club1, "desricption of "+club1, "www.urlclub1.de"));
        assertNotNull(testPromoter1Obj);

        // create and save two events for promoter1
        event1Obj = new Event(new Date(), event1, "some stuff", testPromoter1Obj);
        assertNotNull(eventRepository.save(event1Obj));

        event2Obj = new Event(new Date(), event2, "some hot stuff", testPromoter1Obj);
        assertNotNull(eventRepository.save(event2Obj));
    }

    @Test
    public void createNewCompetition() throws Exception {
        this.mockMvc.perform(post("/event/{eventid}/competition/{name}", event1Obj.getId(), competiton1)
                .param("description", description1)
                .param("fee", fee1.toString()))
                .andExpect(status().isCreated());

        // get events for promoter
        List<Competition> competitions = competitionRepository.findByEventId(event1Obj.getId());
        assertNotNull(competitions);

        // check, if list contains our new event
        Competition firstCompetition = competitions.get(0);
        assertNotNull(firstCompetition);
        assertEquals(firstCompetition.getName(), competiton1);
    }

    @Test
    public void getCompetitionsForEvent() throws Exception {
        // create and save two events for promoter1
        Competition competition1Obj = new Competition(competiton1, description1, fee1, event1Obj);
        assertNotNull(competitionRepository.save(competition1Obj));

        Competition competition2Obj = new Competition(competiton2, description2, fee2, event1Obj);
        assertNotNull(competitionRepository.save(competition2Obj));

        // create and save two events for promoter2
        Competition competition3Obj = new Competition(competiton3, description3, fee3, event2Obj);
        assertNotNull(competitionRepository.save(competition3Obj));

        Competition competition4Obj = new Competition(competiton4, description4, fee4, event2Obj);
        assertNotNull(competitionRepository.save(competition4Obj));

        mockMvc.perform(get("/event/{eventid}/competition", event1Obj.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
