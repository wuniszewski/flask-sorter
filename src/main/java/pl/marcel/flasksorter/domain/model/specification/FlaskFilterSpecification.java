package pl.marcel.flasksorter.domain.model.specification;

import pl.marcel.flasksorter.domain.model.Flask;

import java.util.function.Predicate;

public interface FlaskFilterSpecification extends Predicate<Flask> {

    @Override
    boolean test(Flask flask);
}
