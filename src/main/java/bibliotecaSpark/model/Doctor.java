package bibliotecaSpark.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.naming.directory.InvalidAttributeValueException;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Doctor extends Person {
    @Setter(AccessLevel.NONE)
    private int doctorId;
    private String crm;
    private String specialty;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static int idCounter;

    public Doctor(
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "address", required = true) Address address,
            @JsonProperty(value = "crm", required = true) String crm,
            @JsonProperty(value = "specialty", required = true) String specialty) throws Exception {
        super(name, address);
        validate(crm, specialty);

        this.crm = crm;
        this.specialty = specialty;
        this.doctorId = ++idCounter;
    }

    private static void validate(String crm, String specialty) throws InvalidAttributeValueException {
        if(crm == null || crm.isBlank())
            throw new InvalidAttributeValueException("Required property 'crm' is blank ");
        if(specialty == null || specialty.isBlank())
            throw new InvalidAttributeValueException("Required property 'specialty' is blank ");
    }
}
