package healu.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Laboratory extends Schedule {
    private String sampleType;
    private String result;

    public Laboratory(
            String datetime,
            Patient patient,
            Doctor doctor,
            MedicalCare medicalCare,
            String type
    ) {
        super(datetime, patient, doctor, medicalCare, type);
    }
}
