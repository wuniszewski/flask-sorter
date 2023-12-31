package pl.marcel.flasksorter.domain.ports;

import pl.marcel.flasksorter.domain.model.Rack;

import java.util.Set;

public interface RackPort {

    Set<Rack> findAllRacks();
}
