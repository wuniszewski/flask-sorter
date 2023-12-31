package pl.marcel.flasksorter.domain.model.specification;

import lombok.RequiredArgsConstructor;
import pl.marcel.flasksorter.domain.model.Flask;

@RequiredArgsConstructor
public class DifferentAgeSpec implements FlaskFilterSpecification {

    private final Flask flask;

    @Override
    public boolean test(Flask flaskCompare) {
        return !flask.getPatientAge().equals(flaskCompare.getPatientAge());
    }
}
