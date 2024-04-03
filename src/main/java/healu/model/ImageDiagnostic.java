package healu.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ImageDiagnostic extends Schedule {
    private String examType;
    private String result;

    public ImageDiagnostic(
            String datetime,
            Patient patient,
            Doctor doctor,
            MedicalCare medicalCare,
            String type
    ) {
        super(datetime, patient, doctor, medicalCare, type);
    }
}
