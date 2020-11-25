package oop.exams.generator;

import oop.exams.exception.BadRegionException;
import oop.exams.exception.NotAvailableLicensePlateException;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NorthLicensePlateGeneratorTest {

    @Test
    public void givenANorthState_whenGenerate_thenLicensePlateStartsWith1() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new NorthLicensePlateGenerator();
        String state = "SON";

        // When:
        String licensePlate = licensePlateProvider.generate(state);

        // Then:
        assertThat(licensePlate).startsWith("1");
        assertThat(licensePlate.substring(1)).startsWith(state);
        assertThat(licensePlate).hasSize(6);
        assertThat(licensePlate.substring(4)).containsOnlyDigits();
    }

    @Test
    public void givenANorthState_whenGenerateTwice_thenLicensesAreDifferent() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new NorthLicensePlateGenerator();

        // When:
        String licensePlate1 = licensePlateProvider.generate("CHH");
        String licensePlate2 = licensePlateProvider.generate("CHH");

        // Then:
        assertThat(licensePlate1).isNotEqualTo(licensePlate2);
    }

    @Test
    public void givenANonNorthState_whenGenerate_thenBadRegionExceptionIsThrown() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new NorthLicensePlateGenerator();
        String randomStateAbbreviation = "X" + RandomString.make(2);

        // When:
        // Then:
        assertThatThrownBy(() -> licensePlateProvider.generate(randomStateAbbreviation))
                .isInstanceOf(BadRegionException.class)
                .hasMessage("Allowed state codes: BCN, BCS, CHH, COA, NLE, SON, TAM");
    }

    //Test added to cover the 100%
    @Test
    public void compareTwoIdenticalPlates(){
        //Given:
        NorthLicensePlateGenerator northLicensePlateGenerator = new NorthLicensePlateGenerator();
        northLicensePlateGenerator.addPlate("1BCS46");
        northLicensePlateGenerator.addPlate("1TAM74");

        //When:

        //Then:
        assertFalse(northLicensePlateGenerator.validatePlates("1BCS46"));
    }

    @Test
    public void GivenAFullList_WhenTryToAddANewPlate_ThenThrowsAnException() {
        //Given:
        NorthLicensePlateGenerator northLicensePlateGenerator = new NorthLicensePlateGenerator();
        northLicensePlateGenerator.addPlate("1BCS84");
        northLicensePlateGenerator.addPlate("1TAM82");
        northLicensePlateGenerator.addPlate("1BCN38");
        northLicensePlateGenerator.addPlate("1CHH46");
        northLicensePlateGenerator.addPlate("1NLE60");

        //When:

        //Then:
        assertThatThrownBy(() -> northLicensePlateGenerator.generate("1SON59"))
                .isInstanceOf(NotAvailableLicensePlateException.class)
                .hasMessage("No hay más placas disponibles");
    }
}
