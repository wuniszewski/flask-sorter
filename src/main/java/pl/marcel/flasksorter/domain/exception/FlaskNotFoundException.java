package pl.marcel.flasksorter.domain.exception;

import pl.marcel.flasksorter.domain.model.FlaskID;

public class FlaskNotFoundException extends RuntimeException {

    public FlaskNotFoundException(FlaskID flaskID) {
        super(String.format("Flask with ID: %s not found.", flaskID.sampleID()));
    }
}
