package pl.marcel.flasksorter.domain.model;

import pl.marcel.flasksorter.domain.model.specification.*;

import java.util.List;
import java.util.Set;

public class Rack {
    private final RackID rackID;
    private final Set<Flask> flasks;
    private List<FlaskFilterSpecification> specifications;

    public Rack(RackID rackID, Set<Flask> flasks) {
        this.rackID = rackID;
        this.flasks = flasks;
    }

    public boolean assignFlask(Flask flask) {
        prepareSpecifications(flask);
        boolean isRackEligible = flasks.stream()
                .allMatch(this::flaskPassesAllSpecifications);
        if (isRackEligible) flask.storeInRack(this.rackID);
        return isRackEligible;
    }

    private void prepareSpecifications(Flask flask) {
        specifications = List.of(
                new DifferentAgeSpec(flask),
                new DifferentCityDistrictSpec(flask),
                new DifferentCompanyNameSpec(flask),
                new DifferentVisionDefectSpec(flask));
    }

    private boolean flaskPassesAllSpecifications(Flask flask) {
        return specifications.stream()
                .allMatch(spec -> spec.test(flask));
    }
}
