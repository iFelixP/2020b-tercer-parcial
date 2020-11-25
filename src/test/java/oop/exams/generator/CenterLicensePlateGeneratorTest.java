package oop.exams.generator;

import oop.exams.exception.BadRegionException;
import oop.exams.exception.NotAvailableLicensePlateException;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CenterLicensePlateGeneratorTest {

    @Test
    public void givenACenterState_whenGenerate_thenLicensePlateStartsWith1() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new CenterLicensePlateGenerator();
        String state = "SLP";

        // When:
        String licensePlate = licensePlateProvider.generate(state);

        // Then:
        assertThat(licensePlate).startsWith("5");
        assertThat(licensePlate).hasSize(8);
        assertThat(licensePlate).containsOnlyDigits();
    }

    @Test
    public void givenACenterState_whenGenerateTwice_thenLicensesAreDifferent() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new CenterLicensePlateGenerator();

        // When:
        String licensePlate1 = licensePlateProvider.generate("ZAC");
        String licensePlate2 = licensePlateProvider.generate("ZAC");

        // Then:
        assertThat(licensePlate1).isNotEqualTo(licensePlate2);
    }

    @Test
    public void givenANonCenterState_whenGenerate_thenBadRegionExceptionIsThrown() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new CenterLicensePlateGenerator();
        String randomStateAbbreviation = "X" + RandomString.make(2);

        // When:
        // Then:
        assertThatThrownBy(() -> licensePlateProvider.generate(randomStateAbbreviation))
                .isInstanceOf(BadRegionException.class)
                .hasMessage("Allowed state codes: AGU, CMX, DUR, GUA, HID, MEX, PUE, QUE, SLP, TLA, ZAC");
    }

    //Test added to cover the 100%
    @Test
    public void compareTwoIdenticalPlates(){
        //Given:
        CenterLicensePlateGenerator centerLicensePlateGenerator = new CenterLicensePlateGenerator();
        centerLicensePlateGenerator.addPlate("59173702");
        centerLicensePlateGenerator.addPlate("52017482");

        //When:

        //Then:
        assertFalse(centerLicensePlateGenerator.validatePlates("52017482"));
    }

    @Test
    public void GivenAFullList_WhenTryToAddANewPlate_ThenThrowsAnException() {
        //Given:
        CenterLicensePlateGenerator centerLicensePlateGenerator = new CenterLicensePlateGenerator();
        centerLicensePlateGenerator.addPlate("59173702");
        centerLicensePlateGenerator.addPlate("52017482");
        centerLicensePlateGenerator.addPlate("57294738");
        centerLicensePlateGenerator.addPlate("55826446");
        centerLicensePlateGenerator.addPlate("53759360");

        //When:

        //Then:
        assertThatThrownBy(() -> centerLicensePlateGenerator.generate("53728459"))
                .isInstanceOf(NotAvailableLicensePlateException.class)
                .hasMessage("No hay más placas disponibles");
    }
}
