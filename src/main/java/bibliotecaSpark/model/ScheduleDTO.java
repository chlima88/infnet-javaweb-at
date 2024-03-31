package bibliotecaSpark.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
    private String datetime;
    private int patientId;
    private int doctorId;
    private int medicalCareId;
}
