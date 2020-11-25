package oop.exams.service;

import oop.exams.exception.NotAvailableLicensePlateException;
import oop.exams.generator.LicensePlateGenerator;
import oop.exams.generator.LicensePlateGeneratorFactory;
import oop.exams.model.Region;
import oop.exams.repository.LicensePlateRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class LicensePlateServiceTest {

    @Test
    public void givenAValidState_whenGenerate_thenLicensePlateIsReturned() throws NotAvailableLicensePlateException {
        // Given:
        LicensePlateGeneratorFactory factory = mock(LicensePlateGeneratorFactory.class);
        LicensePlateRepository repository = mock(LicensePlateRepository.class);
        LicensePlateGenerator generator = mock(LicensePlateGenerator.class);
        LicensePlateService licensePlateService = new LicensePlateService(factory, repository);
        String state = "SLP";
        String expectedLicensePlate = "ABC1234";

        when(repository.getRegionByState(state)).thenReturn(Region.CENTER);
        when(repository.countByRegion(Region.CENTER)).thenReturn(1);
        when(factory.getInstance(Region.CENTER)).thenReturn(generator);
        when(generator.generate(state)).thenReturn(expectedLicensePlate);

        // When:
        String licensePlate = licensePlateService.generate(state);

        // Then:
        assertThat(licensePlate).isEqualTo(expectedLicensePlate);
        verify(repository).getRegionByState(state);
        verify(repository).countByRegion(Region.CENTER);
        verify(factory).getInstance(Region.CENTER);
        verify(generator).generate(state);
        verify(repository).save(Region.CENTER, licensePlate);
        verifyNoMoreInteractions(repository, factory, generator);
    }

    @Test
    public void WhenGenerateASixthPlate_ThenThrowAnException() {
        //Given:
        LicensePlateGeneratorFactory licensePlateFactory = Mockito.mock(LicensePlateGeneratorFactory.class);
        LicensePlateRepository licensePlateRepository = Mockito.mock(LicensePlateRepository.class);
        LicensePlateService licensePlateService = new LicensePlateService(licensePlateFactory,licensePlateRepository);
        String stateCode = "";

        Mockito.when(licensePlateRepository.getRegionByState(stateCode)).thenReturn(Region.CENTER);
        Mockito.when(licensePlateRepository.countByRegion(Region.CENTER)).thenReturn(5);

        //When:

        //Then:
        assertThatThrownBy(() -> licensePlateService.generate(stateCode)).isInstanceOf(NotAvailableLicensePlateException.class).hasMessage("No hay más placas disponibles");
    }
}