package pl.marcel.flasksorter.domain.model;

import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@UtilityClass
public class RackModelUtils {

    public static Rack createRackWithoutFlask() {
        RackID rackID = new RackID(UUID.fromString("9ceb10fc-7d8f-4843-beb0-bfb1b5286265"));
        return new Rack(rackID, Collections.emptySet());
    }

    public static Rack createRackWithFlask() {
        RackID rackID = new RackID(UUID.fromString("9ceb10fc-7d8f-4843-beb0-bfb1b5286265"));
        Flask flask = FlaskModelUtils.createFlaskWithRack(10, "PCN1", "PCD1", "PVD1", rackID);
        return new Rack(rackID, Set.of(flask));
    }
}
