package pl.marcel.flasksorter.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter()
public class Flask {

    private final FlaskID flaskID;
    private final Integer patientAge;
    private final String patientCompanyName;
    private final String patientCityDistrict;
    private final String patientVisionDefect;
    private RackID rackID;

    public void storeInRack(RackID rackID) {
        this.rackID = rackID;
    }

    public boolean isFlaskAssigned() {
        return this.rackID != null;
    }
}
