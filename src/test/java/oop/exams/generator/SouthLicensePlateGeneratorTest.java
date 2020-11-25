package oop.exams.generator;

import oop.exams.exception.BadRegionException;
import oop.exams.exception.NotAvailableLicensePlateException;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SouthLicensePlateGeneratorTest {

    @Test
    public void givenASouthState_whenGenerate_thenLicensePlateStartsWith1() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new SouthLicensePlateGenerator();
        String state = "OAX";

        // When:
        String licensePlate = licensePlateProvider.generate(state);

        // Then:
        assertThat(licensePlate).startsWith("4");
        assertThat(licensePlate).hasSize(7);
        assertThat(licensePlate).contains(state);
    }

    @Test
    public void givenASouthState_whenGenerateTwice_thenLicensesAreDifferent() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new SouthLicensePlateGenerator();

        // When:
        String licensePlate1 = licensePlateProvider.generate("GRO");
        String licensePlate2 = licensePlateProvider.generate("GRO");

        // Then:
        assertThat(licensePlate1).isNotEqualTo(licensePlate2);
    }

    @Test
    public void givenANonSouthState_whenGenerate_thenBadRegionExceptionIsThrown() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new SouthLicensePlateGenerator();
        String randomStateAbbreviation = "X" + RandomString.make(2);

        // When:
        // Then:
        assertThatThrownBy(() -> licensePlateProvider.generate(randomStateAbbreviation))
                .isInstanceOf(BadRegionException.class)
                .hasMessage("Allowed state codes: CHP, GRO, MIC, OAX");
    }

    //Test added to cover the 100%
    @Test
    public void compareTwoIdenticalPlates(){
        //Given:
        SouthLicensePlateGenerator southLicensePlateGenerator = new SouthLicensePlateGenerator();
        southLicensePlateGenerator.addPlate("46GRO85");
        southLicensePlateGenerator.addPlate("48OAX37");

        //When:

        //Then:
        assertFalse(southLicensePlateGenerator.validatePlates("48OAX37"));
    }

    @Test
    public void GivenAFullList_WhenTryToAddANewPlate_ThenThrowsAnException() {
        //Given:
        SouthLicensePlateGenerator southLicensePlateGenerator = new SouthLicensePlateGenerator();
        southLicensePlateGenerator.addPlate("49CHP02");
        southLicensePlateGenerator.addPlate("42GRO82");
        southLicensePlateGenerator.addPlate("47MIC38");
        southLicensePlateGenerator.addPlate("45OAX46");
        southLicensePlateGenerator.addPlate("43CHO60");

        //When:

        //Then:
        assertThatThrownBy(() -> southLicensePlateGenerator.generate("43GRO59"))
                .isInstanceOf(NotAvailableLicensePlateException.class)
                .hasMessage("No hay más placas disponibles");
    }
}
