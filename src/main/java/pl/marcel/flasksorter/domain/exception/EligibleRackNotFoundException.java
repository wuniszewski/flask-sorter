package pl.marcel.flasksorter.domain.exception;

import pl.marcel.flasksorter.domain.model.FlaskID;

public class EligibleRackNotFoundException extends RuntimeException {

    public EligibleRackNotFoundException(FlaskID flaskID) {
        super(String.format("No eligible rack found for flask %s.", flaskID.sampleID()));
    }
}
