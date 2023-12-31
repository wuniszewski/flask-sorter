package pl.marcel.flasksorter.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.marcel.flasksorter.domain.exception.FlaskNotFoundException;
import pl.marcel.flasksorter.domain.model.Flask;
import pl.marcel.flasksorter.domain.model.FlaskID;
import pl.marcel.flasksorter.domain.ports.FlaskPort;

@Service
@RequiredArgsConstructor
public class FlaskFacade {

    private final FlaskPort flaskPort;

    public Flask getFlask(FlaskID flaskID) {
        return flaskPort.findOne(flaskID)
                .orElseThrow(() -> new FlaskNotFoundException(flaskID));
    }
}
