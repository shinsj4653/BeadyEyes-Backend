package gdsc.pointer.controller;

import gdsc.pointer.domain.User;
import gdsc.pointer.dto.ResultDto;
import gdsc.pointer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(ResultDto.res(HttpStatus.OK, "회원 조회 완료", users));
    }


//    @PostMapping("/insertUser")
//    public ResponseEntity<?> insertUser(@RequestBody User user) throws Exception{
//        return userService.insertUser(user);
//    }
//
//    @GetMapping("/getUserDetail")
//    public ResponseEntity<?> getUserDetail(@RequestParam("id") String id) throws Exception{
//        return userService.getUserDetail(id);
//    }
//
//    @PutMapping("/updateUser/{id}")
//    public ResponseEntity<?> updateUser(@PathVariable("id") String id, @RequestBody User user) throws Exception{
//        return userService.updateUser(id, user);
//    }
//
//    @DeleteMapping("/deleteUser/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) throws Exception{
//        return userService.deleteUser(id);
//    }

}
