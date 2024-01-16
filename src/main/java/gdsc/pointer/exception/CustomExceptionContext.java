package gdsc.pointer.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomExceptionContext implements ExceptionContext {

    // BAD_REQUEST
    DUPLICATE_USER_ID_ERROR("이미 구글 로그인을 통해 회원가입을 진행한 이력이 있습니다.", 1000),

    // NOT_FOUND
    USER_NOT_FOUND_ERROR("존재하지 않는 회원입니다.", 2000);

    // UNAUTHORIZED
    private final String message;
    private final int code;
}
