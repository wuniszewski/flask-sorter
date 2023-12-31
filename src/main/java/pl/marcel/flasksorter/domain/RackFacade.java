package pl.marcel.flasksorter.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.marcel.flasksorter.domain.exception.EligibleRackNotFoundException;
import pl.marcel.flasksorter.domain.exception.FlaskAlreadyAssignedException;
import pl.marcel.flasksorter.domain.exception.FlaskNotFoundException;
import pl.marcel.flasksorter.domain.model.Flask;
import pl.marcel.flasksorter.domain.model.FlaskID;
import pl.marcel.flasksorter.domain.ports.FlaskPort;
import pl.marcel.flasksorter.domain.ports.RackPort;

@Service
@RequiredArgsConstructor
public class RackFacade {

    private final FlaskPort flaskPort;
    private final RackPort rackPort;

    public void assignFlask(FlaskID flaskID) {
        Flask flask = flaskPort.findOne(flaskID).orElseThrow(() -> new FlaskNotFoundException(flaskID));
        if (flask.isFlaskAssigned()) throw new FlaskAlreadyAssignedException(flask.getRackID());

        rackPort.findAllRacks().stream()
                .filter(rack -> rack.assignFlask(flask))
                .findFirst()
                .orElseThrow(() -> new EligibleRackNotFoundException(flaskID));
        flaskPort.save(flask);
    }
}
