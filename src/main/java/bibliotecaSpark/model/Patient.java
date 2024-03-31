package bibliotecaSpark.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;


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

    public Patient(@JsonProperty(value = "name", required = true) String name, @JsonProperty(value = "address", required = true) Address address) {
        super(name,address);
        ++idCounter;
        this.patientId = idCounter;
    }
}

