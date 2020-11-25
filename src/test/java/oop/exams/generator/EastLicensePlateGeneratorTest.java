package oop.exams.generator;

import oop.exams.exception.BadRegionException;
import oop.exams.exception.NotAvailableLicensePlateException;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EastLicensePlateGeneratorTest {

    @Test
    public void givenAEastState_whenGenerate_thenLicensePlateStartsWith1() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new EastLicensePlateGenerator();
        String state = "VER";

        // When:
        String licensePlate = licensePlateProvider.generate(state);

        // Then:
        assertThat(licensePlate).startsWith("3");
        assertThat(licensePlate).endsWith("Z");
        assertThat(licensePlate).hasSize(4);
        assertThat(licensePlate.substring(0, 3)).containsOnlyDigits();
    }

    @Test
    public void givenAEastState_whenGenerateTwice_thenLicensesAreDifferent() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new EastLicensePlateGenerator();

        // When:
        String licensePlate1 = licensePlateProvider.generate("TAB");
        String licensePlate2 = licensePlateProvider.generate("TAB");

        // Then:
        assertThat(licensePlate1).isNotEqualTo(licensePlate2);
    }

    @Test
    public void givenANonEastState_whenGenerate_thenBadRegionExceptionIsThrown() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new EastLicensePlateGenerator();
        String randomStateAbbreviation = "X" + RandomString.make(2);

        // When:
        // Then:
        assertThatThrownBy(() -> licensePlateProvider.generate(randomStateAbbreviation))
                .isInstanceOf(BadRegionException.class)
                .hasMessage("Allowed state codes: CAM, ROO, TAB, VER, YUC");
    }

    //Test added to cover the 100%
    @Test
    public void compareTwoIdenticalPlates(){
        //Given:
        EastLicensePlateGenerator eastLicensePlateGenerator = new EastLicensePlateGenerator();
        eastLicensePlateGenerator.addPlate("336Z");
        eastLicensePlateGenerator.addPlate("372Z");

        //When:

        //Then:
        assertFalse(eastLicensePlateGenerator.validatePlates("372Z"));
    }

    @Test
    public void GivenAFullList_WhenTryToAddANewPlate_ThenThrowsAnException() {
        //Given:
        EastLicensePlateGenerator eastLicensePlateGenerator = new EastLicensePlateGenerator();
        eastLicensePlateGenerator.addPlate("367Z");
        eastLicensePlateGenerator.addPlate("354Z");
        eastLicensePlateGenerator.addPlate("310Z");
        eastLicensePlateGenerator.addPlate("305Z");
        eastLicensePlateGenerator.addPlate("376Z");

        //When:

        //Then:
        assertThatThrownBy(() -> eastLicensePlateGenerator.generate("361Z"))
                .isInstanceOf(NotAvailableLicensePlateException.class)
                .hasMessage("No hay más placas disponibles");
    }
}
