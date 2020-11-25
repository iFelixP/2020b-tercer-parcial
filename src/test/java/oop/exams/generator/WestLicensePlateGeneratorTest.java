package oop.exams.generator;

import oop.exams.exception.BadRegionException;
import oop.exams.exception.NotAvailableLicensePlateException;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class WestLicensePlateGeneratorTest {

    @Test
    public void givenAWestState_whenGenerate_thenLicensePlateStartsWith1() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new WestLicensePlateGenerator();
        String state = "SIN";

        // When:
        String licensePlate = licensePlateProvider.generate(state);

        // Then:
        assertThat(licensePlate).startsWith("2");
        assertThat(licensePlate).endsWith(state);
        assertThat(licensePlate).hasSize(5);
        assertThat(licensePlate.substring(0, 2)).containsOnlyDigits();
    }

    @Test
    public void givenAWestState_whenGenerateTwice_thenLicensesAreDifferent() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new WestLicensePlateGenerator();

        // When:
        String licensePlate1 = licensePlateProvider.generate("NAY");
        String licensePlate2 = licensePlateProvider.generate("NAY");

        // Then:
        assertThat(licensePlate1).isNotEqualTo(licensePlate2);
    }

    @Test
    public void givenANonWestState_whenGenerate_thenBadRegionExceptionIsThrown() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new WestLicensePlateGenerator();
        String randomStateAbbreviation = "X" + RandomString.make(2);

        // When:
        // Then:
        assertThatThrownBy(() -> licensePlateProvider.generate(randomStateAbbreviation))
                .isInstanceOf(BadRegionException.class)
                .hasMessage("Allowed state codes: COL, JAL, NAY, SIN");
    }

    //Test added to cover the 100%
    @Test
    public void compareTwoIdenticalPlates(){
        //Given:
        WestLicensePlateGenerator westLicensePlateGenerator = new WestLicensePlateGenerator();
        westLicensePlateGenerator.addPlate("25COL");
        westLicensePlateGenerator.addPlate("28JAL");

        //When:

        //Then:
        assertFalse(westLicensePlateGenerator.validatePlates("28JAL"));
    }

    @Test
    public void GivenAFullList_WhenTryToAddANewPlate_ThenThrowsAnException() {
        //Given:
        WestLicensePlateGenerator westLicensePlateGenerator = new WestLicensePlateGenerator();
        westLicensePlateGenerator.addPlate("25COL");
        westLicensePlateGenerator.addPlate("22NAY");
        westLicensePlateGenerator.addPlate("28JAL");
        westLicensePlateGenerator.addPlate("26SIN");
        westLicensePlateGenerator.addPlate("20COL");

        //When:

        //Then:
        assertThatThrownBy(() -> westLicensePlateGenerator.generate("27JAL"))
                .isInstanceOf(NotAvailableLicensePlateException.class)
                .hasMessage("No hay más placas disponibles");
    }
}
