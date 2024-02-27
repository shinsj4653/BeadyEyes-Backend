package gdsc.pointer.service;

import gdsc.pointer.dao.UserDao;
import gdsc.pointer.domain.User;
import gdsc.pointer.dto.request.login.UserDto;
import gdsc.pointer.exception.badrequest.user.DuplicateUserIdException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public List<User> getUsers() throws Exception {
        return userDao.getUsers();
    }

    @Override
    public User getUserDetail(String id) throws Exception {
        return userDao.getUserDetail(id);
    }

    @Override
    public boolean isUserEmailExist(String email) throws Exception {
        return userDao.isUserEmailExist(email);
    }

    @Override
    public boolean isUserNameExist(String name) throws Exception {
        return userDao.isUserNameExist(name);
    }


    @Override
    public void addUser(UserDto userDto) throws Exception {
        if (validateDuplicateUser(userDto.getId())){
            throw new DuplicateUserIdException();
        }
        userDao.addUser(userDto);
    }

    private boolean validateDuplicateUser(String id) throws Exception {
        if (userDao.getUserDetail(id) != null) {
            // 이미 존재하는 유저인 경우
            return true;
        } else {
            return false;
        }
    }
}
