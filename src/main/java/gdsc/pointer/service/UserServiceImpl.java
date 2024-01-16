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
//
//    @Override
//    public ResponseEntity<?> getUserDetail(String id) throws Exception {
//        Firestore firestore = FirestoreClient.getFirestore();
//        DocumentReference documentReference =
//                firestore.collection(COLLECTION_NAME).document(id);
//        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
//        DocumentSnapshot documentSnapshot = apiFuture.get();
//        User user = null;
//        if(documentSnapshot.exists()){
//            user = documentSnapshot.toObject(User.class);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        }
//        else{
//            return new ResponseEntity<>("존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @Override
//    public ResponseEntity<?> updateUser(String id, User user) throws Exception {
//        Firestore firestore = FirestoreClient.getFirestore();
//        ApiFuture<com.google.cloud.firestore.WriteResult> apiFuture
//                = firestore.collection(COLLECTION_NAME).document(id).set(user);
//        return new ResponseEntity<>("회원 수정 완료", HttpStatus.OK);
//    }
//
//    @Override
//    public ResponseEntity<?> deleteUser(String id) throws Exception {
//        Firestore firestore = FirestoreClient.getFirestore();
//        ApiFuture<WriteResult> apiFuture
//                = firestore.collection(COLLECTION_NAME).document(id).delete();
//        return new ResponseEntity<>("회원 삭제 완료", HttpStatus.OK);
//
//    }
}
