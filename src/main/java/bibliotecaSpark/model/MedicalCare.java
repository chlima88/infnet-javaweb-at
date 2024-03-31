package bibliotecaSpark.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.*;

@Getter
@Setter
public abstract class MedicalCare {
    @Setter(AccessLevel.NONE)
    private int medicalCareId;
    private int durationInMinutes;
    private String code;
    private String name;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static int idCounter;

    public MedicalCare() {
        ++idCounter;
        this.medicalCareId = idCounter;
        this.durationInMinutes = 0;
    }

}
