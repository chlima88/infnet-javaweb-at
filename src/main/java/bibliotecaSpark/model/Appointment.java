package bibliotecaSpark.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Appointment extends Schedule {
    private String diagnostic;
    private String prescription;
    private String symptoms;

    public Appointment(
            String datetime,
            Patient patient,
            Doctor doctor,
            MedicalCare medicalCare,
            String type
    ) throws Exception {
        super(datetime, patient, doctor, medicalCare, type);
    }
}
