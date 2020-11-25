package oop.exams.generator;

import oop.exams.exception.BadRegionException;
import oop.exams.exception.NotAvailableLicensePlateException;

import java.util.ArrayList;

public class WestLicensePlateGenerator implements LicensePlateGenerator {
    ArrayList<String>platesRegistred = new ArrayList<>();

    @Override
    public String generate(String state) throws BadRegionException, NotAvailableLicensePlateException {

        validateAvailability();
        validateState(state);

        int randomNumber;
        String plateGenerated;

        do {
            randomNumber = (int)(Math.random()*10);
            plateGenerated = "2" + randomNumber + state;
        } while(!validatePlates(plateGenerated));

        addPlate(plateGenerated);

        return plateGenerated;
    }

    public boolean validatePlates(String plate){

        for(String plates : platesRegistred) {
            if(plates.equals(plate))
                return false;
        }
        return true;
    }

    public void validateState(String state) throws BadRegionException{

        LicensePlateGeneratorFactory licensePlateGenerator = new LicensePlateGeneratorFactory();

        if(!(licensePlateGenerator.getInstance(state) instanceof WestLicensePlateGenerator)){
            throw new BadRegionException("Allowed state codes: COL, JAL, NAY, SIN");
        }
    }
    public void addPlate(String plate) {
        platesRegistred.add(plate);
    }

    public void validateAvailability() throws NotAvailableLicensePlateException {
        if(platesRegistred.size() == 5){
            throw new NotAvailableLicensePlateException("No hay más placas disponibles");
        }
    }
}
