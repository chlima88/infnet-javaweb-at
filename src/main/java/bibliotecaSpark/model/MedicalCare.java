package bibliotecaSpark.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.naming.directory.InvalidAttributeValueException;

@Getter
@Setter
public class MedicalCare {
    @Setter(AccessLevel.NONE)
    private int medicalCareId;
    private int durationInMinutes;
    private String code;
    private String name;
    private boolean active;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static int idCounter;

    public MedicalCare(
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "code", required = true) String code,
            @JsonProperty(value = "durationInMinutes", required = true) int durationInMinutes
    ) throws Exception {

        if (name == null || name.isBlank())
            throw new InvalidAttributeValueException("Required property 'name' is blank ");
        if (code == null || code.isBlank())
            throw new InvalidAttributeValueException("Required property 'code' is blank ");
        if (durationInMinutes < 15)
            throw new InvalidAttributeValueException("durationInMinutes should be bigger than 15");

        this.durationInMinutes = durationInMinutes;
        this.active = true;
        this.code = code;
        this.name = name;
        this.medicalCareId = ++idCounter;
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

}
