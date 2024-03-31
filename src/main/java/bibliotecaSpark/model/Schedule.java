package bibliotecaSpark.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Appointment.class),
        @JsonSubTypes.Type(value = Laboratory.class),
        @JsonSubTypes.Type(value = ImageDiagnosis.class) }
)
public class Schedule {
    @Setter(AccessLevel.NONE)
    private int scheduleId;
    private LocalDateTime datetime;
    private boolean confirmed;
    private Patient patient;
    private Doctor doctor;
    private MedicalCare medicalCare;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static int idCounter;

    public Schedule() {
        ++idCounter;
        this.scheduleId = idCounter;
        this.confirmed = false;
    }

    public Schedule(String datetime, Patient patient, Doctor doctor, MedicalCare medicalCare) {
        this();
        this.patient = patient;
        this.doctor = doctor;
        this.medicalCare = medicalCare;
    }

    public void setDatetime(String datetime) {
        this.datetime = LocalDateTime.parse(datetime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
