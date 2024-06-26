package unifor.devweb.project.freelearn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MethodArgumentTypeMismatchException extends RuntimeException {
    public MethodArgumentTypeMismatchException(String message) {
        super(message);
    }
}