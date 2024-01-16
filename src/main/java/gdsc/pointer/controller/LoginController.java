package gdsc.pointer.controller;

import gdsc.pointer.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/login/oauth2", produces = "application/json")
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/code/{registrationId}")
    public void googleLogin(@RequestParam String code, @PathVariable String registrationId) {
        loginService.socialLogin(code, registrationId);
    }

}
