package pl.marcel.flasksorter.domain.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.marcel.flasksorter.domain.model.FlaskModelUtils.createFlaskWithNoRack;

class FlaskTest {

    @Test
    void shouldReturnTrueWhenFlaskHasAssignedRack() {
        //given
        Flask flask = createFlaskWithNoRack(20, "PCN1", "PCD1", "PVD1");
        flask.storeInRack(new RackID(UUID.fromString("27e8a973-cd1b-477a-8080-fb71c3d21a7b")));

        //when
        boolean isFlaskAssigned = flask.isFlaskAssigned();

        //then
        assertTrue(isFlaskAssigned);
    }

    @Test
    void shouldReturnFalseWhenFlaskHasNotAssignedRack() {
        //given
        Flask flask = createFlaskWithNoRack(20, "PCN1", "PCD1", "PVD1");

        //when
        boolean isFlaskAssigned = flask.isFlaskAssigned();

        //then
        assertFalse(isFlaskAssigned);
    }
}