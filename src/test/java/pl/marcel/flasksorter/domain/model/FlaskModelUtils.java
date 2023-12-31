package pl.marcel.flasksorter.domain.model;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class FlaskModelUtils {

    public static Flask createFlaskWithNoRack(Integer age, String companyName, String cityDistrict, String visionDefect) {
        return new Flask(new FlaskID(UUID.fromString("c00db482-bf70-46ab-8a0d-46fff2dcc750")),
                age, companyName, cityDistrict, visionDefect, null);
    }

    public static Flask createFlaskWithRack(Integer age, String companyName, String cityDistrict, String visionDefect, RackID rackID) {
        return new Flask(new FlaskID(UUID.fromString("d55c8f08-be81-49e5-acf7-2b952b9d1b9a")),
                age, companyName, cityDistrict, visionDefect, rackID);
    }
}
