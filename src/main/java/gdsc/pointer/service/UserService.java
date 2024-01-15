package gdsc.pointer.service;

import gdsc.pointer.domain.User;
import gdsc.pointer.dto.request.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> getUsers() throws Exception;
    void addUser(UserDto userDto) throws Exception;
}
