package pl.marcel.flasksorter.domain.model.specification;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pl.marcel.flasksorter.domain.model.Flask;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.marcel.flasksorter.domain.model.FlaskModelUtils.createFlaskWithNoRack;

class FlaskFilterSpecificationTest {

    @ParameterizedTest
    @ValueSource(classes = {DifferentAgeSpec.class, DifferentCompanyNameSpec.class, DifferentCityDistrictSpec.class, DifferentVisionDefectSpec.class})
    void shouldReturnTrueWhenTestingFlasksWithDifferentIndividualData(Class argumentClass) throws Exception {
        //given
        Flask flask = createFlaskWithNoRack(10, "PCN1", "PCD1", "PVD1");
        Flask entirelyDifferentFlask = createFlaskWithNoRack(20, "PCN2", "PCD2", "PVD2");

        //when
        FlaskFilterSpecification specification = (FlaskFilterSpecification) argumentClass.getDeclaredConstructor(Flask.class).newInstance(flask);

        //then
        assertTrue(specification.test(entirelyDifferentFlask));
    }

    @ParameterizedTest
    @ValueSource(classes = {DifferentAgeSpec.class, DifferentCompanyNameSpec.class, DifferentCityDistrictSpec.class, DifferentVisionDefectSpec.class})
    void shouldReturnFalseWhenTestingFlasksWithSameIndividualData(Class argumentClass) throws Exception {
        //given
        Flask flask = createFlaskWithNoRack(10, "PCN1", "PCD1", "PVD1");
        Flask sameDataFlask = createFlaskWithNoRack(10, "PCN1", "PCD1", "PVD1");

        //when
        FlaskFilterSpecification specification = (FlaskFilterSpecification) argumentClass.getDeclaredConstructor(Flask.class).newInstance(flask);

        //then
        assertFalse(specification.test(sameDataFlask));
    }
}