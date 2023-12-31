package pl.marcel.flasksorter.adapters.api;

import pl.marcel.flasksorter.domain.model.Flask;

import java.util.UUID;

public record FlaskResponse(
        UUID flaskID,
        Integer patientAge,
        String patientCompanyName,
        String patientCityDistrict,
        String patientVisionDefect,
        UUID rackID
) {
    public static FlaskResponse of(Flask flask) {
        var rackID = flask.isFlaskAssigned() ? flask.getRackID().rackID() : null;
        return new FlaskResponse(
                flask.getFlaskID().sampleID(),
                flask.getPatientAge(),
                flask.getPatientCompanyName(),
                flask.getPatientCityDistrict(),
                flask.getPatientVisionDefect(),
                rackID);
    }
}
