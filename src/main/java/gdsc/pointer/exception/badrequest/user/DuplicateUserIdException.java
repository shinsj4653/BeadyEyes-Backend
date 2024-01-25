package gdsc.pointer.exception.badrequest.user;

import gdsc.pointer.exception.ExceptionContext;
import gdsc.pointer.exception.badrequest.BadRequestException;
import lombok.Getter;

import static gdsc.pointer.exception.CustomExceptionContext.DUPLICATE_USER_ID_ERROR;

@Getter
public class DuplicateUserIdException extends BadRequestException {

    public DuplicateUserIdException() {
        super(DUPLICATE_USER_ID_ERROR);
    }
}
