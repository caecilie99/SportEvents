package sportevent;

/**
 * Created by Birgit on 10.12.2016.
 */
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import sportevent.controller.UserController;
import sportevent.dao.ClubRepository;
import sportevent.dao.UserRepository;
import sportevent.model.Club;
import sportevent.model.User;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.hamcrest.CoreMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@WebMvcTest(UserController.class)
public class UserControllerRESTTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));


    private MockMvc mockMvc;
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private UserRepository userRepository;

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

        // start with enpty tables
        this.clubRepository.deleteAll();
        this.userRepository.deleteAll();
    }

    @Test
    public void shouldCreateNewUser() throws Exception {
        //User newUser = new User("Doe", "John", "test@mail.com", "doe", "sport");
        this.mockMvc.perform(post("/user/{username}", "doe").param("lastname", "Doe").param("firstname", "John")
                .param("email", "test@mail.com").param("password", "$2a$06$XS6vFeumbiaJR1TIxAxazO7QyaWfy62oIA8e0Ww5/rjhlI4ro0J5m")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        assertNotNull(userRepository.findByUsername("doe"));
    }

    @Test
    public void shouldCreateAndUpdateUser() throws Exception {
        User newUser = new User("Doe", "John", "test@mail.com", "doe", "$2a$06$XS6vFeumbiaJR1TIxAxazO7QyaWfy62oIA8e0Ww5/rjhlI4ro0J5m");
        assertNotNull(userRepository.save(newUser));

        this.mockMvc.perform(put("/user/{id}", newUser.getId()).param("firstname", "Mike")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
        User updatedUser = userRepository.findByUsername("doe");
        assertThat(updatedUser.getFirstname(), is("Mike"));

        this.mockMvc.perform(put("/user/{id}", newUser.getId()).param("lastname", "Smith")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
        updatedUser = userRepository.findByUsername("doe");
        assertThat(updatedUser.getLastname(), is("Smith"));

        this.mockMvc.perform(put("/user/{id}", newUser.getId()).param("email", "new@mail.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
        updatedUser = userRepository.findByUsername("doe");
        assertThat(updatedUser.getEmail(), is("new@mail.com"));

        this.mockMvc.perform(put("/user/{id}", newUser.getId()).param("zipcode", "12345")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
        updatedUser = userRepository.findByUsername("doe");
        assertThat(updatedUser.getZipcode(), is("12345"));

        this.mockMvc.perform(put("/user/{id}", newUser.getId()).param("city", "Berlin")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
        updatedUser = userRepository.findByUsername("doe");
        assertThat(updatedUser.getCity(), is("Berlin"));

        this.mockMvc.perform(put("/user/{id}", newUser.getId()).param("adress", "Lange Strasse 4711")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
        updatedUser = userRepository.findByUsername("doe");
        assertThat(updatedUser.getAdress(), is("Lange Strasse 4711"));

        this.mockMvc.perform(put("/user/{id}", newUser.getId()).param("phone", "030/99999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
        updatedUser = userRepository.findByUsername("doe");
        assertThat(updatedUser.getPhone(), is("030/99999"));
    }

    @Test
    public void shouldFindUserByName() throws Exception {
        User newUser = new User("Doe", "John", "test@mail.com", "doe", "$2a$06$XS6vFeumbiaJR1TIxAxazO7QyaWfy62oIA8e0Ww5/rjhlI4ro0J5m");
        assertNotNull(userRepository.save(newUser));

        this.mockMvc.perform(get("/user/{name}", newUser.getUsername())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.firstname", is("John")))
                .andExpect(jsonPath("$.lastname", is("Doe")))
                .andExpect(jsonPath("$.email", is("test@mail.com")));
    }

    @Test
    public void shouldFindClubByUserName() throws Exception {
        User newUser = new User("Doe", "John", "test@mail.com", "doe", "$2a$06$XS6vFeumbiaJR1TIxAxazO7QyaWfy62oIA8e0Ww5/rjhlI4ro0J5m");
        assertNotNull(userRepository.save(newUser));

        Club newClub = new Club("TSV Hintertupfingen");
        newClub.setUser(newUser);
        assertNotNull(clubRepository.save(newClub));

        this.mockMvc.perform(get("/user/{name}/club", newUser.getUsername())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("TSV Hintertupfingen")));
    }

}
