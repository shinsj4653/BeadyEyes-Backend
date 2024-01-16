package gdsc.pointer.exception.notfound;

import gdsc.pointer.exception.ExceptionContext;
import gdsc.pointer.exception.PointerException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends PointerException {
    public NotFoundException(ExceptionContext context) {
        super(HttpStatus.NOT_FOUND, context.getMessage(), context.getCode());
    }
}
