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
import sportevent.dao.PromoterRepository;
import sportevent.model.Club;
import sportevent.model.Contact;
import sportevent.model.Promoter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class PromoterControllerRESTTest {

    private static final String club1 = "VfL Schnelle Hasen";
    private static final String club2 = "TuS Muckibude";
    private static final String club3 = "Die Wellness-Oase";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private PromoterRepository promoterRepository;

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
        this.promoterRepository.deleteAll();
    }

    @Test
    public void createNewClub() throws Exception {
        this.mockMvc.perform(post("/club/" + club1))
                .andExpect(status().isCreated());
    }

    @Test
    public void createAndUpdateClub() throws Exception {
        // create new club

        this.mockMvc.perform(post("/promoter/{name}", club1)
                .param("description", "desricption of"+club1)
                .param("url",  "www.coolewebsite.de"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("http://localhost/promoter/")));
        Promoter newPromoter = promoterRepository.findByName(club1);
        newPromoter.setName(club2);

        mockMvc.perform(
                put("/promoter/{id}", newPromoter.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.json(newPromoter)))
                .andExpect(status().isOk());    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }


}
