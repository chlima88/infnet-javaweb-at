package bibliotecaSpark.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public abstract class Person {
    @NonNull
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate birthday;
    private User user;
    @NonNull
    private Address address;


    public void setBirthday(String birthday) {
        this.birthday = LocalDate.parse(birthday, DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
