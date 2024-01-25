package gdsc.pointer.exception.badrequest;

import gdsc.pointer.exception.ExceptionContext;
import gdsc.pointer.exception.PointerException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends PointerException {

    public BadRequestException(ExceptionContext context) {
        super(HttpStatus.BAD_REQUEST, context.getMessage(), context.getCode());
    }
}
