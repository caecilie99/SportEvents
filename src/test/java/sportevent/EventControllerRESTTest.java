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
import org.springframework.web.context.WebApplicationContext;
import sportevent.dao.ClubRepository;
import sportevent.dao.ContactRepository;
import sportevent.dao.EventRepository;
import sportevent.dao.PromoterRepository;
import sportevent.model.Club;
import sportevent.model.Contact;
import sportevent.model.Event;
import sportevent.model.Promoter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class EventControllerRESTTest {

    private Promoter testPromoter;

    private static final String club1 = "Die Wellness-Oase";

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

        // start with empty tables
        this.eventRepository.deleteAll();
        this.promoterRepository.deleteAll();

        // create club and get ID to use for tests
        testPromoter = promoterRepository.save(new Promoter(club1, "desricption of "+club1, "www.urlclub1.de"));
        assertNotNull(testPromoter);
    }

    @Test
    public void createNewEvent() throws Exception {
        this.mockMvc.perform(post("/event/{name}/{promoterid}", event1, testPromoter.getId())
                .param("date", Long.toString(new Date().getTime()))
                .param("description", "nice event"))
                .andExpect(status().isCreated());

        // get events for promoter
        List<Event> promoterEvents = eventRepository.findByPromoter(testPromoter);
        assertNotNull(promoterEvents);

        // check, if list contains our new event
        Event firstEvent = promoterEvents.get(0);
        assertNotNull(firstEvent);
        assertEquals(firstEvent.getName(), event1);
    }

    @Test
    public void createAndUpdateEvent() throws Exception {
        this.mockMvc.perform(post("/event/{name}/{promoterid}", event1, testPromoter.getId())
                .param("date", Long.toString(new Date().getTime()))
                .param("description", "nice event"))
                .andExpect(status().isCreated());

        // get events for promoter
        List<Event> promoterEvents = eventRepository.findByPromoter(testPromoter);
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

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }


}
