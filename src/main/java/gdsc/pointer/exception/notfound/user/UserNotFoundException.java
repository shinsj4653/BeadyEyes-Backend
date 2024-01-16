package gdsc.pointer.exception.notfound.user;

import gdsc.pointer.exception.notfound.NotFoundException;

import static gdsc.pointer.exception.CustomExceptionContext.USER_NOT_FOUND_ERROR;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super(USER_NOT_FOUND_ERROR);
    }
}
