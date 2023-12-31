package pl.marcel.flasksorter.adapters.db;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.marcel.flasksorter.domain.model.Rack;
import pl.marcel.flasksorter.domain.ports.RackPort;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
class DBRackRepository implements RackPort {

    private final RackModelRepository rackModelRepository;

    @Override
    public Set<Rack> findAllRacks() {
        var allRacks = rackModelRepository.findAll();
        return allRacks.stream()
                .map(RackModel::toDomain)
                .collect(Collectors.toSet());
    }
}
