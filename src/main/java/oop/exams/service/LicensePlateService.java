package oop.exams.service;

import oop.exams.exception.NotAvailableLicensePlateException;
import oop.exams.generator.LicensePlateGenerator;
import oop.exams.generator.LicensePlateGeneratorFactory;
import oop.exams.model.Region;
import oop.exams.repository.LicensePlateRepository;

public class LicensePlateService {

    private static final int MAX_LICENSES_BY_REGION = 5;

    private LicensePlateGeneratorFactory licensePlateGeneratorFactory;
    private LicensePlateRepository licensePlateRepository;

    public LicensePlateService(LicensePlateGeneratorFactory licensePlateGeneratorFactory, LicensePlateRepository licensePlateRepository) {
        this.licensePlateGeneratorFactory = licensePlateGeneratorFactory;
        this.licensePlateRepository = licensePlateRepository;
    }


    public String generate(String stateCode) throws NotAvailableLicensePlateException {
        Region region = licensePlateRepository.getRegionByState(stateCode);
        int count = licensePlateRepository.countByRegion(region);

        if(count == MAX_LICENSES_BY_REGION){
            throw new NotAvailableLicensePlateException("No hay más placas disponibles");
        }

        LicensePlateGenerator generator = licensePlateGeneratorFactory.getInstance(region);

        String licensePlate = generator.generate(stateCode);

        licensePlateRepository.save(region, licensePlate);

        return licensePlate;
    }
}
