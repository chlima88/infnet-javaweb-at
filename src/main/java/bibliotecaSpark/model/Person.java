package bibliotecaSpark.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.naming.directory.InvalidAttributeValueException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Person {
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate birthday;
    private Address address;

    public Person(String name, Address address) throws Exception {
        validate(name, address);
        this.name = name;
        this.address = address;
    }


    public void setBirthday(String birthday) throws InvalidAttributeValueException {
        if(birthday == null || !birthday.matches("\\d{4}-\\d{2}-\\d{2}"))
            throw new InvalidAttributeValueException("Required property 'birthday' must have ISO date format");
        this.birthday = LocalDate.parse(birthday, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    private static void validate(String name, Address address) throws InvalidAttributeValueException {
        if(name == null || name.isBlank())
            throw new InvalidAttributeValueException("Required property 'name' cannot be blank or null");
        if(address.getCep() == null || address.getCep().isBlank())
            throw new InvalidAttributeValueException("Required property 'address.cep' is blank or null");
    }
}
