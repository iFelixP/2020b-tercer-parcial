package oop.exams.repository;

import oop.exams.exception.BadRegionException;
import oop.exams.model.Region;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class LicensePlateRepositoryTest {

    @Test
    public void GivenDiferentsStates_WhenGetRegionByState_ThenTheCorrectRegionIsReturned() {
        //Given:
        LicensePlateRepository licensePlateRepository = new LicensePlateRepository();
        String north = "BCN";
        String east = "CAM";
        String west = "COL";
        String south = "CHP";
        String center = "AGU";
        //When:

        //Then:
        assertEquals(Region.NORTH,licensePlateRepository.getRegionByState(north));
        assertEquals(Region.EAST,licensePlateRepository.getRegionByState(east));
        assertEquals(Region.WEST,licensePlateRepository.getRegionByState(west));
        assertEquals(Region.SOUTH,licensePlateRepository.getRegionByState(south));
        assertEquals(Region.CENTER,licensePlateRepository.getRegionByState(center));

    }

    @Test
    public void GivenAnInexistentState_WhenGetRegionByState_ThenThrowsAnException() {
        //Given:
        LicensePlateRepository licensePlateRepository = new LicensePlateRepository();
        String unknown = "XYZ";
        //When:

        //Then:
        assertThatThrownBy(() -> licensePlateRepository.getRegionByState(unknown)).isInstanceOf(BadRegionException.class).hasMessage("Estado desconocido");
    }

    @Test
    public void GivenAListOfPlates_WhenCountByRegion_ThenReturnTheSize(){
        //Given:
        LicensePlateRepository licensePlateRepository = new LicensePlateRepository();
        licensePlateRepository.save(Region.NORTH,"PLATE1");

        licensePlateRepository.save(Region.EAST,"PLATE1");
        licensePlateRepository.save(Region.EAST,"PLATE2");

        licensePlateRepository.save(Region.WEST,"PLATE1");
        licensePlateRepository.save(Region.WEST,"PLATE2");
        licensePlateRepository.save(Region.WEST,"PLATE3");

        licensePlateRepository.save(Region.SOUTH,"PLATE1");
        licensePlateRepository.save(Region.SOUTH,"PLATE2");
        licensePlateRepository.save(Region.SOUTH,"PLATE3");
        licensePlateRepository.save(Region.SOUTH,"PLATE4");

        licensePlateRepository.save(Region.CENTER,"PLATE1");

        //When:

        //Then:
        assertEquals(1,licensePlateRepository.countByRegion(Region.NORTH));
        assertEquals(2,licensePlateRepository.countByRegion(Region.EAST));
        assertEquals(3,licensePlateRepository.countByRegion(Region.WEST));
        assertEquals(4,licensePlateRepository.countByRegion(Region.SOUTH));
        assertEquals(1,licensePlateRepository.countByRegion(Region.CENTER));
    }
}
