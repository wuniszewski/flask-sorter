package pl.marcel.flasksorter.domain.exception;

import pl.marcel.flasksorter.domain.model.RackID;

public class FlaskAlreadyAssignedException extends RuntimeException {

    public FlaskAlreadyAssignedException(RackID rackID) {
        super(String.format("Flask already assigned to rack %s.", rackID.rackID()));
    }
}
