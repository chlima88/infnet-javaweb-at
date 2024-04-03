package healu.exception;

import java.io.Serial;

public class DuplicatedEntityException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;
    public DuplicatedEntityException(String message) {
        super(message);
    }
}
