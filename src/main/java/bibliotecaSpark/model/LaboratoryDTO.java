package bibliotecaSpark.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class LaboratoryDTO extends ScheduleDTO {
    private String sampleType;
    private String result;

    public LaboratoryDTO(
            @JsonProperty(value = "datetime", required = true)  String datetime,
            @JsonProperty(value = "patientId", required = true) int patientId,
            @JsonProperty(value = "doctorId", required = true) int doctorId,
            @JsonProperty(value = "medicalCareId", required = true) int medicalCareId,
            @JsonProperty(value = "type", required = true) String type) throws Exception {
        super(datetime, patientId, doctorId, medicalCareId, type);

    }
}


