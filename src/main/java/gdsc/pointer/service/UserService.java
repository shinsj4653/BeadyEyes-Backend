package gdsc.pointer.service;

import gdsc.pointer.domain.User;
import gdsc.pointer.dto.request.login.UserDto;

import java.util.List;

public interface UserService {
    List<User> getUsers() throws Exception;

    User getUserDetail(String id) throws Exception;
    void addUser(UserDto userDto) throws Exception;
}
