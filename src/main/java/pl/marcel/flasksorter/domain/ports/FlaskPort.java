package pl.marcel.flasksorter.domain.ports;

import pl.marcel.flasksorter.domain.model.Flask;
import pl.marcel.flasksorter.domain.model.FlaskID;

import java.util.Optional;

public interface FlaskPort {

    Optional<Flask> findOne(FlaskID sampleID);

    void save(Flask flask);
}
