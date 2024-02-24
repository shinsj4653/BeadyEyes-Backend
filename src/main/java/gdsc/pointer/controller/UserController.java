package gdsc.pointer.controller;

import gdsc.pointer.domain.User;
import gdsc.pointer.dto.request.login.UserDto;
import gdsc.pointer.dto.response.ResultDto;
import gdsc.pointer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("getUsers")
    public ResponseEntity<?> getUsers() throws Exception {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, "모든 회원 조회 완료", users));
    }
    @GetMapping("/getUserDetail/{id}")
    public ResponseEntity<?> getUserDetail(@PathVariable("id") String id) throws Exception {
        User user =  userService.getUserDetail(id);
        if (user == null) {
            return ResponseEntity.ok(ResultDto.res(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."));
        }
        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, "회원 정보 조회 완료", user));
    }

    @GetMapping("/isUserEmailExist")
    public ResponseEntity<?> isUserEmailExist(@RequestParam("email") String email) throws Exception {
        boolean isExist = userService.isUserEmailExist(email);
        if (isExist) {
            return ResponseEntity.ok(ResultDto.res(HttpStatus.BAD_REQUEST, "중복되는 회원 이메일입니다."));
        }
        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, "중복되지 않는 회원 이메일입니다."));
    }

    @GetMapping("/isUserNameExist")
    public ResponseEntity<?> isUserNameExist(@RequestParam("name") String name) throws Exception {
        boolean isExist = userService.isUserNameExist(name);
        if (isExist) {
            return ResponseEntity.ok(ResultDto.res(HttpStatus.BAD_REQUEST, "중복되는 회원 이름입니다."));
        }
        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, "중복되지 않는 회원 이름입니다."));
    }

    @PostMapping()
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) throws Exception{
        userService.addUser(userDto);
        return ResponseEntity.ok(ResultDto.res(HttpStatus.CREATED, "회원 가입 완료"));
    }

}
