package oop.exams.generator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DefaultPlateGeneratorTest {

    // Test added to cover 100%
    @Test
    public void WhenSentSomethingToDefault_ThenGenerateReturnNull() {
        //Given:
        LicensePlateGenerator licensePlateGenerator = new DefaultPlateGenerator();

        //When:

        //Then:
        assertNull(licensePlateGenerator.generate("PRUEBA"));
    }
}
