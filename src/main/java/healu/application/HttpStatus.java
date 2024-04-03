package healu.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum HttpStatus {
    OK(200, "OK"),
    CREATED(201, "Created"),
    ACCEPTED(202, "Accepted"),
    NO_CONTENT(204, "No Content"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int code;
    private final String status;

    public static HttpStatus get(int code) {
        return Arrays.stream(HttpStatus.values())
                .filter(status -> status.code == code)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not implemented status code: " + code));
    }
    @Override
    public String toString() {
        return String.valueOf(code);
    }

}
