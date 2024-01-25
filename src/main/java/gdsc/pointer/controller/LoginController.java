package gdsc.pointer.controller;

import gdsc.pointer.dto.request.login.UserDto;
import gdsc.pointer.dto.response.ResultDto;
import gdsc.pointer.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/login/oauth2", produces = "application/json")
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/code/{registrationId}")
    public ResponseEntity<?> googleLogin(@RequestParam String code, @PathVariable String registrationId) throws Exception {
        UserDto userDto = loginService.socialLogin(code, registrationId);
        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, "회원 가입 완료", userDto));
    }

}
