package bibliotecaSpark.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@Builder
public class RestExceptionHandler {
    private String timestamp;
    private int code;
    private String status;
    private String message;

    public RestExceptionHandler(){
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.getCode();
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.getStatus();
    }

    public RestExceptionHandler code(HttpStatus code){
        this.code = code.getCode();
        this.status = code.getStatus();
        return this;
    }

    public RestExceptionHandler code(int code){
        this.code = code;
        this.status = HttpStatus.get(code).getStatus();
        return this;
    }

    public RestExceptionHandler message(String message) {
        this.message = message;
        return this;
    }

    public String build() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }
}
