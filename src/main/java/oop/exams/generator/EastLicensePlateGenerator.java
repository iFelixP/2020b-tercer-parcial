package oop.exams.generator;

import oop.exams.exception.BadRegionException;
import oop.exams.exception.NotAvailableLicensePlateException;

import java.util.ArrayList;
public class EastLicensePlateGenerator implements LicensePlateGenerator {
    ArrayList<String> platesRegistred = new ArrayList<>();
    @Override
    public String generate(String state) throws BadRegionException, NotAvailableLicensePlateException{

        validateAvailability();
        validateState(state);

        int randomNumberA;
        int randomNumberB;
        String plateGenerated;

        do {
            randomNumberA = (int)(Math.random()*10);
            randomNumberB = (int)(Math.random()*10);
            plateGenerated = "3" + randomNumberA + randomNumberB + "Z";
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

        if(!(licensePlateGenerator.getInstance(state) instanceof EastLicensePlateGenerator)){
            throw new BadRegionException("Allowed state codes: CAM, ROO, TAB, VER, YUC");
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
