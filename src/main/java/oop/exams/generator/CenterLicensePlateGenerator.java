package oop.exams.generator;

import oop.exams.exception.BadRegionException;
import oop.exams.exception.NotAvailableLicensePlateException;

import java.util.ArrayList;

public class CenterLicensePlateGenerator implements LicensePlateGenerator {

    ArrayList<String> platesRegistred = new ArrayList<>();

    @Override
    public String generate(String state) throws BadRegionException, NotAvailableLicensePlateException {

        validateAvailability();
        validateState(state);

        String plateGenerated = "5";

        do {
            for(int index = 0 ; index < 7 ; index++)
                plateGenerated = plateGenerated + (int) (Math.random() * 10);
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

        if(!(licensePlateGenerator.getInstance(state) instanceof CenterLicensePlateGenerator)){
            throw new BadRegionException("Allowed state codes: AGU, CMX, DUR, GUA, HID, MEX, PUE, QUE, SLP, TLA, ZAC");
        }
    }
    public void addPlate(String plate) {
        platesRegistred.add(plate);
    }

    public void validateAvailability() throws NotAvailableLicensePlateException{
        if(platesRegistred.size() == 5){
            throw new NotAvailableLicensePlateException("No hay más placas disponibles");
        }
    }
}
