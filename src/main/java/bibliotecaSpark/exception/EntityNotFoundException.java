package bibliotecaSpark.exception;

import java.io.Serial;

public class EntityNotFoundException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;
    public  EntityNotFoundException(String message) {
        super(message);
    }
}
