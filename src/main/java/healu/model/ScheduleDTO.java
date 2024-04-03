package healu.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.naming.directory.InvalidAttributeValueException;

@Getter
public abstract class ScheduleDTO {
    @JsonProperty(value = "datetime", required = true)
    private final String datetime;
    private final int patientId;
    private final int doctorId;
    private final int medicalCareId;
    private final String type;

    public ScheduleDTO (
            String datetime,
            int patientId,
            int doctorId,
            int medicalCareId,
            String type) throws Exception {
        validate( datetime, patientId, doctorId, medicalCareId, type);

        this.datetime = datetime;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.medicalCareId = medicalCareId;
        this.type = type;
    }

    private static void validate(String datetime, int patientId,int doctorId,int medicalCareId, String type) throws InvalidAttributeValueException {
        if(datetime == null || !datetime.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}.*"))
            throw new InvalidAttributeValueException("Required property 'datetime' must have ISO date-time format");
        if (datetime.isBlank())
            throw new InvalidAttributeValueException("Required property 'datetime' cannot be blank ");
        if (patientId <= 0)
            throw new InvalidAttributeValueException("Required property 'patientId' cannot have a value smaller than 1. Received: " + patientId);
        if (doctorId <= 0)
            throw new InvalidAttributeValueException("Required property 'patientId' cannot have a value smaller than 1. Received: " + doctorId);
        if (medicalCareId <= 0)
            throw new InvalidAttributeValueException("Required property 'patientId' cannot have a value smaller than 1. Received: " + medicalCareId);
        if (type == null || type.isBlank())
            throw new InvalidAttributeValueException("Required property 'type' cannot be blank ");
    }
}
