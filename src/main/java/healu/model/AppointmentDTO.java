package healu.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AppointmentDTO extends ScheduleDTO {
    private String prescription;
    private String diagnostic;
    private String symptoms;

    public AppointmentDTO(
            @JsonProperty(value = "datetime", required = true)  String datetime,
            @JsonProperty(value = "patientId", required = true) int patientId,
            @JsonProperty(value = "doctorId", required = true) int doctorId,
            @JsonProperty(value = "medicalCareId", required = true) int medicalCareId,
            @JsonProperty(value = "type", required = true) String type) throws Exception {
        super(datetime, patientId, doctorId, medicalCareId, type);

    }
}


