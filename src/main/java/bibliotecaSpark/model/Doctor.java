package bibliotecaSpark.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

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

    @JsonCreator
    public Doctor(@JsonProperty("name") String name, @JsonProperty("address") Address address) {
        super(name, address);
        ++idCounter;
        this.doctorId = idCounter;
    }
}
