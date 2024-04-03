package healu.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class HttpResponse {
    private String timestamp;
    private int code;
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private HttpData data;

    public HttpResponse(){
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.getCode();
        this.status = HttpStatus.INTERNAL_SERVER_ERROR.getStatus();
//        this.data = new HttpData();
    }

    public HttpResponse code(HttpStatus code){
        this.code = code.getCode();
        this.status = code.getStatus();
        return this;
    }

    public HttpResponse code(int code){
        this.code = code;
        this.status = HttpStatus.get(code).getStatus();
        return this;
    }

    public HttpResponse message(String message) {
        this.data = this.data == null ? new HttpData() : this.data;
        this.data.setMessage(message);
        return this;
    }

    public HttpResponse exception(String exception) {
        this.data = this.data == null ? new HttpData() : this.data;
        this.data.setErrorCause(exception);
        return this;
    }

    @SneakyThrows
    public String build(){
        return new ObjectMapper().writeValueAsString(this);
    }
}
