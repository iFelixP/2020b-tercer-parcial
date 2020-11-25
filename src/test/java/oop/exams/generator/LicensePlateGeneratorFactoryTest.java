package oop.exams.generator;

import oop.exams.model.Region;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LicensePlateGeneratorFactoryTest {

    private LicensePlateGeneratorFactory licensePlateGeneratorFactory;

    @BeforeEach
    public void setup(){
        licensePlateGeneratorFactory = new LicensePlateGeneratorFactory();
    }

    @Test
    public void givenANorthRegionState_whenGetInstance_thenNorthRegionGeneratorIsReturned() {
        // Given:
        String []northStates = {"BCN", "BCS", "CHH", "COA", "NLE", "SON", "TAM"};

        for(String state: northStates) {
            // When:
            LicensePlateGenerator instance = licensePlateGeneratorFactory.getInstance(state);

            // Then:
            assertThat(instance).isInstanceOf(NorthLicensePlateGenerator.class);
        }
    }

    @Test
    public void givenASouthRegionState_whenGetInstance_thenSouthRegionGeneratorIsReturned() {
        // Given:
        String []southStates = {"CHP", "GRO", "MIC", "OAX"};

        for(String state: southStates) {
            // When:
            LicensePlateGenerator instance = licensePlateGeneratorFactory.getInstance(state);

            // Then:
            assertThat(instance).isInstanceOf(SouthLicensePlateGenerator.class);
        }
    }

    @Test
    public void givenAEastRegionState_whenGetInstance_thenEastRegionGeneratorIsReturned() {
        // Given:
        String []southStates = {"CAM", "ROO", "TAB", "VER", "YUC"};

        for(String state: southStates) {
            // When:
            LicensePlateGenerator instance = licensePlateGeneratorFactory.getInstance(state);

            // Then:
            assertThat(instance).isInstanceOf(EastLicensePlateGenerator.class);
        }
    }

    @Test
    public void givenAWestRegionState_whenGetInstance_thenWestRegionGeneratorIsReturned() {
        // Given:
        String []southStates = {"COL", "JAL", "NAY", "SIN"};

        for(String state: southStates) {
            // When:
            LicensePlateGenerator instance = licensePlateGeneratorFactory.getInstance(state);

            // Then:
            assertThat(instance).isInstanceOf(WestLicensePlateGenerator.class);
        }
    }

    @Test
    public void givenACenterRegionState_whenGetInstance_thenCenterGeneratorIsReturned() {
        // Given:
        String []centerStates = {"AGU", "CMX", "DUR", "GUA", "HID", "MEX", "PUE", "QUE", "SLP", "TLA", "ZAC"};

        for(String state: centerStates) {
            // When:
            LicensePlateGenerator instance = licensePlateGeneratorFactory.getInstance(state);

            // Then:
            assertThat(instance).isInstanceOf(CenterLicensePlateGenerator.class);
        }
    }

    //Test added to cover the 100%
    @Test
    public void WhenAnyRegionIsSent_ThenTheCorrectObjectIsReturned(){
        //Given:
        LicensePlateGeneratorFactory licensePlateGeneratorFactory = new LicensePlateGeneratorFactory();

        //When:

        //Then:
        assertThat(licensePlateGeneratorFactory.getInstance(Region.NORTH)).isInstanceOf(NorthLicensePlateGenerator.class);
        assertThat(licensePlateGeneratorFactory.getInstance(Region.SOUTH)).isInstanceOf(SouthLicensePlateGenerator.class);
        assertThat(licensePlateGeneratorFactory.getInstance(Region.EAST)).isInstanceOf(EastLicensePlateGenerator.class);
        assertThat(licensePlateGeneratorFactory.getInstance(Region.WEST)).isInstanceOf(WestLicensePlateGenerator.class);
        assertThat(licensePlateGeneratorFactory.getInstance(Region.CENTER)).isInstanceOf(CenterLicensePlateGenerator.class);
    }

}