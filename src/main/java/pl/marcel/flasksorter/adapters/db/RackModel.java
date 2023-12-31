package pl.marcel.flasksorter.adapters.db;

import jakarta.persistence.*;
import pl.marcel.flasksorter.domain.model.Rack;
import pl.marcel.flasksorter.domain.model.RackID;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "RACK_MODEL")
class RackModel {

    @Id
    @Column(name = "RACK_MODEL_ID")
    private UUID rackModelID;

    @Column
    @OneToMany(mappedBy = "rackModel", cascade = CascadeType.MERGE)
    private Set<FlaskModel> flasks;

    public RackModel() {
    }

    UUID rackModelID() {
        return rackModelID;
    }

    Rack toDomain() {
        return new Rack(new RackID(rackModelID), flasks.stream().map(FlaskModel::toDomain).collect(Collectors.toSet()));
    }
}
