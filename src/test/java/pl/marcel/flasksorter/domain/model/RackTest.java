package pl.marcel.flasksorter.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static pl.marcel.flasksorter.domain.model.FlaskModelUtils.*;
import static pl.marcel.flasksorter.domain.model.RackModelUtils.*;

class RackTest {

    @Test
    void shouldAssignFlaskAndReturnTrueWhenRackIsEmpty() {
        //given
        Rack emptyRack = createRackWithoutFlask();
        Flask flask = createFlaskWithNoRack(20, "PCN1", "PCD1", "PVD1");

        //when
        boolean isFlaskAssigned = emptyRack.assignFlask(flask);

        //then
        assertTrue(isFlaskAssigned);
        assertNotNull(flask.getRackID());
    }

    @Test
    void shouldAssignFlaskAndReturnTrueWhenAllSpecificationAreMet() {
        //given
        Rack rack = createRackWithFlask();
        Flask entirelyDifferentFlask = createFlaskWithNoRack(30, "PCN2", "PCD2", "PVD2");

        //when
        boolean isFlaskAssigned = rack.assignFlask(entirelyDifferentFlask);

        //then
        assertTrue(isFlaskAssigned);
        assertNotNull(entirelyDifferentFlask.getRackID());
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsForIndividualSpecificationsNotMet")
    void shouldNotAssignFlaskAndReturnFalseWhenIndividualSpecificationIsNotMet(Integer age, String companyName, String cityDistrict, String visionDefect) {
        //given
        Rack rack = createRackWithFlask();
        Flask entirelyDifferentFlask = createFlaskWithNoRack(age, companyName, cityDistrict, visionDefect);

        //when
        boolean isFlaskAssigned = rack.assignFlask(entirelyDifferentFlask);

        //then
        assertFalse(isFlaskAssigned);
        assertNull(entirelyDifferentFlask.getRackID());
    }

    private static Stream<Arguments> provideArgumentsForIndividualSpecificationsNotMet() {
        return Stream.of(
                Arguments.of(10, "PCN2", "PCD2", "PVD2"),
                Arguments.of(20, "PCN1", "PCD2", "PVD2"),
                Arguments.of(20, "PCN2", "PCD1", "PVD2"),
                Arguments.of(20, "PCN2", "PCD2", "PVD1"));
    }
}