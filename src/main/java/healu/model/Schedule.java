package healu.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Schedule {
    @Setter(AccessLevel.NONE)
    private int scheduleId;
    @EqualsAndHashCode.Include
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime datetime;
    private boolean confirmed;
    private String type;
    @EqualsAndHashCode.Include
    private Patient patient;
    @EqualsAndHashCode.Include
    private Doctor doctor;
    private MedicalCare medicalCare;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static int idCounter;

    public Schedule(
            String datetime,
            Patient patient,
            Doctor doctor,
            MedicalCare medicalCare,
            String type) {
        this.patient = patient;
        this.doctor = doctor;
        this.medicalCare = medicalCare;
        this.type = type;
        setDatetime(datetime);
        this.confirmed = false;
        this.scheduleId = ++idCounter;
    }

    public void setDatetime(String datetime) {
        this.datetime = LocalDateTime.parse(datetime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
