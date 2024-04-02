package bibliotecaSpark.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.naming.directory.InvalidAttributeValueException;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class Patient extends Person {
    @Setter(AccessLevel.NONE)
    private int patientId;
    private String document;
    private String contact;
    private boolean insurance;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static int idCounter;

    @JsonCreator
    public Patient(
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "address", required = true) Address address,
            @JsonProperty(value = "document", required = true) String document,
            @JsonProperty(value = "insurance", required = true) boolean insurance) throws Exception {
        super(name,address);
        validate(document);

        this.insurance = insurance;
        this.document = document;
        this.patientId = ++idCounter;
    }

    private static void validate(String document) throws InvalidAttributeValueException {

        if (document == null || document.isBlank())
            throw new InvalidAttributeValueException("Required property 'document' is blank ");
    }
}

