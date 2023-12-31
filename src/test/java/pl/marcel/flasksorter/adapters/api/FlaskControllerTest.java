package pl.marcel.flasksorter.adapters.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static pl.marcel.flasksorter.adapters.api.IntegrationTestUtils.EXISTING_FLASK_ID;
import static pl.marcel.flasksorter.adapters.api.IntegrationTestUtils.NON_EXISTING_FLASK_ID;

@SpringBootTest
@AutoConfigureMockMvc
class FlaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnFlaskGivenOneExists() throws Exception {
        //when
        ResultActions result = mockMvc.perform(get("/api/flask/" + EXISTING_FLASK_ID).contentType(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.flaskID", is(EXISTING_FLASK_ID.toString())));
    }

    @Test
    void shouldReturn404GivenNotExistingFlask() throws Exception {
        //when
        ResultActions result = mockMvc.perform(get("/api/flask/" + NON_EXISTING_FLASK_ID).contentType(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isNotFound())
                .andExpect(content().string(String.format("Flask with ID: %s not found.", NON_EXISTING_FLASK_ID)));
    }
}