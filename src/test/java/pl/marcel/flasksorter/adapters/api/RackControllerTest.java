package pl.marcel.flasksorter.adapters.api;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.marcel.flasksorter.domain.model.Flask;
import pl.marcel.flasksorter.domain.model.FlaskID;
import pl.marcel.flasksorter.domain.ports.FlaskPort;

import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.marcel.flasksorter.adapters.api.IntegrationTestUtils.*;

@SpringBootTest
@AutoConfigureMockMvc
class RackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FlaskPort flaskPort;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void cleanUp() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("UPDATE FLASK_MODEL SET RACK_MODEL_ID=null").executeUpdate();
        em.getTransaction().commit();
    }

    @Test()
    void shouldThrowFlaskNotFoundExceptionGivenNotExistingFlask() throws Exception {
        //given
        //when
        ResultActions result = mockMvc.perform(put("/api/rack/flask/" + NON_EXISTING_FLASK_ID));

        //then
        result.andExpect(status().isNotFound())
                .andExpect(content().string(String.format("Flask with ID: %s not found.", NON_EXISTING_FLASK_ID)));
    }

    @Test
    void shouldAssignRackGivenExistingFlaskAndAllEmptyRacks() throws Exception {
        //given
        FlaskID existingFlaskID = new FlaskID(EXISTING_FLASK_ID);

        //when
        ResultActions result = mockMvc.perform(put("/api/rack/flask/" + existingFlaskID.sampleID()));
        Flask flaskFetched = flaskPort.findOne(existingFlaskID).get();

        //then
        result.andExpect(status().isOk());
        assertEquals(existingFlaskID, flaskFetched.getFlaskID());
        assertNotNull(flaskFetched.getRackID());
    }

    @ParameterizedTest
    @MethodSource("providePairsOfIdenticalDistinctPatientDataFlaskIDs")
    void shouldAssignDifferentRacksGivenSameIndividualPatientDataFlasks(UUID firstFlaskUUID, UUID secondFlaskUUID) throws Exception {
        //given
        FlaskID firstFlaskID = new FlaskID(firstFlaskUUID);
        FlaskID secondFlaskID = new FlaskID(secondFlaskUUID);

        //when
        ResultActions firstResult = mockMvc.perform(put("/api/rack/flask/" + firstFlaskID.sampleID()));
        ResultActions secondResult = mockMvc.perform(put("/api/rack/flask/" + secondFlaskID.sampleID()));
        Flask firstFlaskFetched = flaskPort.findOne(firstFlaskID).get();
        Flask secondFlaskFetched = flaskPort.findOne(secondFlaskID).get();

        //then
        firstResult.andExpect(status().isOk());
        secondResult.andExpect(status().isOk());
        assertNotNull(firstFlaskFetched.getRackID());
        assertNotNull(secondFlaskFetched.getRackID());
        assertNotEquals(firstFlaskFetched.getRackID(), secondFlaskFetched.getRackID());
    }

    @Test
    void shouldReturn400WithFlaskAlreadyAssignedMessageGivenFlaskAlreadyAssigned() throws Exception {
        //given
        FlaskID existingFlaskID = new FlaskID(EXISTING_FLASK_ID);
        mockMvc.perform(put("/api/rack/flask/" + existingFlaskID.sampleID()));
        Flask flaskFetched = flaskPort.findOne(existingFlaskID).get();

        //when
        ResultActions result = mockMvc.perform(put("/api/rack/flask/" + existingFlaskID.sampleID()));

        //then
        result.andExpect(status().isBadRequest())
                .andExpect(content().string(String.format("Flask already assigned to rack %s.", flaskFetched.getRackID().rackID())));
    }

    @Test
    void shouldReturn404WhenNoEligibleRacksFound() throws Exception {
        //given
        FlaskID firstFlaskID = new FlaskID(THREE_IDENTICAL_FLASKS_IDS.get(0));
        FlaskID secondFlaskID = new FlaskID(THREE_IDENTICAL_FLASKS_IDS.get(1));
        FlaskID thirdFlaskID = new FlaskID(THREE_IDENTICAL_FLASKS_IDS.get(2));
        mockMvc.perform(put("/api/rack/flask/" + firstFlaskID.sampleID()));
        mockMvc.perform(put("/api/rack/flask/" + secondFlaskID.sampleID()));

        //when
        ResultActions result = mockMvc.perform(put("/api/rack/flask/" + thirdFlaskID.sampleID()));

        //then
        result.andExpect(status().isNotFound())
                .andExpect(content().string(String.format("No eligible rack found for flask %s.", thirdFlaskID.sampleID())));
    }

    private static Stream<Arguments> providePairsOfIdenticalDistinctPatientDataFlaskIDs() {
        return Stream.of(
                Arguments.of(TWO_IDENTICAL_ONLY_AGE_FLASKS_IDS.get(0), TWO_IDENTICAL_ONLY_AGE_FLASKS_IDS.get(1)),
                Arguments.of(TWO_IDENTICAL_ONLY_COMPANY_NAME_FLASKS_IDS.get(0), TWO_IDENTICAL_ONLY_COMPANY_NAME_FLASKS_IDS.get(1)),
                Arguments.of(TWO_IDENTICAL_ONLY_CITY_DISTRICT_FLASKS_IDS.get(0), TWO_IDENTICAL_ONLY_CITY_DISTRICT_FLASKS_IDS.get(1)),
                Arguments.of(TWO_IDENTICAL_ONLY_VISION_DEFECT_FLASKS_IDS.get(0), TWO_IDENTICAL_ONLY_VISION_DEFECT_FLASKS_IDS.get(1)));
    }
}