package pl.marcel.flasksorter.adapters.db;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.marcel.flasksorter.domain.model.Flask;
import pl.marcel.flasksorter.domain.model.FlaskID;
import pl.marcel.flasksorter.domain.ports.FlaskPort;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
class DBFlaskRepository implements FlaskPort {

    private final FlaskModelRepository flaskModelRepository;
    private final RackModelRepository rackModelRepository;

    @Override
    public Optional<Flask> findOne(FlaskID sampleID) {
        return flaskModelRepository.findById(sampleID.sampleID()).map(FlaskModel::toDomain);
    }

    @Override
    public void save (Flask flask) {
        var flaskModel = FlaskModel.ofWithoutRackID(flask);
        if (flask.isFlaskAssigned()) {
            flaskModel.setRackModel(rackModelRepository.getReferenceById(flask.getRackID().rackID()));
        }
        flaskModelRepository.save(flaskModel);
    }
}
