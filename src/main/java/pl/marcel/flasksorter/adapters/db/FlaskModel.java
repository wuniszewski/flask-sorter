package pl.marcel.flasksorter.adapters.db;

import jakarta.persistence.*;
import pl.marcel.flasksorter.domain.model.Flask;
import pl.marcel.flasksorter.domain.model.FlaskID;
import pl.marcel.flasksorter.domain.model.RackID;

import java.util.UUID;

@Entity
@Table(name = "FLASK_MODEL")
class FlaskModel {

    @Id
    @Column(name = "FLASK_MODEL_ID")
    private UUID flaskModelID;

    @Column(nullable = false)
    private Integer patientAge;

    @Column(nullable = false)
    private String patientCompanyName;

    @Column(nullable = false)
    private String patientCityDistrict;

    @Column(nullable = false)
    private String patientVisionDefect;

    @ManyToOne
    @JoinColumn(name = "RACK_MODEL_ID")
    private RackModel rackModel;

    private FlaskModel(UUID flaskModelID, Integer patientAge, String patientCompanyName, String patientCityDistrict, String patientVisionDefect) {
        this.flaskModelID = flaskModelID;
        this.patientAge = patientAge;
        this.patientCompanyName = patientCompanyName;
        this.patientCityDistrict = patientCityDistrict;
        this.patientVisionDefect = patientVisionDefect;
    }

    public FlaskModel() {
    }

    public void setRackModel(RackModel rackModel) {
        this.rackModel = rackModel;
    }

    Flask toDomain() {
        var rackId = (rackModel != null) ? new RackID(rackModel.rackModelID()) : null;
        return new Flask(new FlaskID(flaskModelID),
                patientAge,
                patientCompanyName,
                patientCityDistrict,
                patientVisionDefect,
                rackId);
    }

    static FlaskModel ofWithoutRackID(Flask flask) {
        return new FlaskModel(
                flask.getFlaskID().sampleID(),
                flask.getPatientAge(),
                flask.getPatientCompanyName(),
                flask.getPatientCityDistrict(),
                flask.getPatientVisionDefect());
    }
}
