package sportevent;

/**
 * Created by Birgit on 10.12.2016.
 */
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;
import sportevent.dao.*;
import sportevent.model.Event;
import sportevent.model.Promoter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class EventControllerRESTTest {

    private Promoter testPromoter1Obj;
    private Promoter testPromoter2Obj;

    private static final String club1 = "Die Wellness-Oase";
    private static final String club2 = "FC Stramme Waden";

    private static final String event1 = "Lange Saunanacht";
    private static final String event2 = "24-h Lauf";

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

        // create clubs to use for tests
        testPromoter1Obj = promoterRepository.save(new Promoter(club1, "desricption of "+club1, "www.urlclub1.de"));
        assertNotNull(testPromoter1Obj);

        testPromoter2Obj = promoterRepository.save(new Promoter(club2, "desricption of "+club2, "www.urlclub2.de"));
        assertNotNull(testPromoter2Obj);
    }

    @Test
    public void createNewEvent() throws Exception {
        this.mockMvc.perform(post("/event/{name}", event1).param("promoterid", testPromoter1Obj.getId().toString())
                .param("date", Long.toString(new Date().getTime()))
                .param("description", "nice event"))
                .andExpect(status().isCreated());

        // get events for promoter
        List<Event> promoterEvents = eventRepository.findByPromoterId(testPromoter1Obj.getId());
        assertNotNull(promoterEvents);

        // check, if list contains our new event
        Event firstEvent = promoterEvents.get(0);
        assertNotNull(firstEvent);
        assertEquals(firstEvent.getName(), event1);
    }

    @Test
    public void createAndUpdateEvent() throws Exception {
        this.mockMvc.perform(post("/event/{name}", event1).param("promoterid", testPromoter1Obj.getId().toString())
                .param("date", Long.toString(new Date().getTime()))
                .param("description", "nice event"))
                .andExpect(status().isCreated());

        // get events for promoter
        List<Event> promoterEvents = eventRepository.findByPromoterId(testPromoter1Obj.getId());
        assertNotNull(promoterEvents);

        // check, if list contains our new event
        Event firstEvent = promoterEvents.get(0);
        assertNotNull(firstEvent);
        assertEquals(firstEvent.getName(), event1);

        firstEvent.setName(event2);
        firstEvent.setDescription("description for "+event2);

        mockMvc.perform(
                put("/event/{id}", firstEvent.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.json(firstEvent)))
                .andExpect(status().isOk());
    }

    @Test
    public void createAndGetEvent() throws Exception {
        ResultActions result = this.mockMvc.perform(post("/event/{name}", event2)
                .param("date", Long.toString(new Date().getTime())) // cast to Long because getTime returns long
                .param("description", "nice event")
                .param("promoterid", testPromoter1Obj.getId().toString()))
                .andExpect(status().isCreated());

        // get new location geturned by post
        String newLocation = result.andReturn().getResponse().getHeader("Location");
        String returnIdStr = newLocation.substring(newLocation.lastIndexOf("/")+1);
        Long returnId = Long.parseLong(returnIdStr);

        mockMvc.perform(
                get("/event/{id}", returnId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("name", is(event2)));
    }

    @Test
    public void getAllEvents() throws Exception {
        // create and save two events
        Event event1Obj = new Event(new Date(), event1, "some stuff", testPromoter1Obj);
        assertNotNull(eventRepository.save(event1Obj));

        Event event2Obj = new Event(new Date(), event2, "some hot stuff", testPromoter1Obj);
        assertNotNull(eventRepository.save(event2Obj));

        mockMvc.perform(get("/event"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)));
    }


    @Test
    public void getEventsForPromoter() throws Exception {
        // create and save two events for promoter1
        Event event1Obj = new Event(new Date(), event1, "some stuff", testPromoter1Obj);
        assertNotNull(eventRepository.save(event1Obj));

        Event event2Obj = new Event(new Date(), event2, "some hot stuff", testPromoter1Obj);
        assertNotNull(eventRepository.save(event2Obj));

        // create and save two events for promoter2
        Event event3Obj = new Event(new Date(), event1, "some stuff", testPromoter2Obj);
        assertNotNull(eventRepository.save(event3Obj));

        Event event4Obj = new Event(new Date(), event2, "some hot stuff", testPromoter2Obj);
        assertNotNull(eventRepository.save(event4Obj));

        mockMvc.perform(get("/event").param("promoterid", testPromoter1Obj.getId().toString()))
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
