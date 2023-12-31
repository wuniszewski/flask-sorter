package pl.marcel.flasksorter.domain.model.specification;

import lombok.RequiredArgsConstructor;
import pl.marcel.flasksorter.domain.model.Flask;

@RequiredArgsConstructor
public class DifferentCompanyNameSpec implements FlaskFilterSpecification {

    private final Flask flask;

    @Override
    public boolean test(Flask flaskCompare) {
        return !flask.getPatientCompanyName().equalsIgnoreCase(flaskCompare.getPatientCompanyName());
    }
}
