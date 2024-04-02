package bibliotecaSpark.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.naming.directory.InvalidAttributeValueException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public abstract class Schedule {
    @Setter(AccessLevel.NONE)
    private int scheduleId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime datetime;
    private boolean confirmed;
    private String type;
    private Patient patient;
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
            String type) throws Exception {
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
