package gdsc.pointer.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import gdsc.pointer.domain.User;
import gdsc.pointer.dto.request.login.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UserDao {

    public static final String COLLECTION_NAME = "User";

    private final Firestore db;

    public List<User> getUsers() throws Exception {
        List<User> list = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            list.add(document.toObject(User.class));
        }
        return list;
    }


    public User getUserDetail(String id) throws Exception {

        DocumentReference documentReference =
                db.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        User user = null;
        if(documentSnapshot.exists()){
            user = documentSnapshot.toObject(User.class);
        }
        return user;
    }

    public boolean isUserEmailExist(String email) throws Exception {

        CollectionReference collection = db.collection(COLLECTION_NAME);
        Query query = collection.whereEqualTo("email", email);

        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

        if(!documents.isEmpty()){
            return true;
        }
        return false;
    }

    public boolean isUserNameExist(String name) throws Exception {

        CollectionReference collection = db.collection(COLLECTION_NAME);
        Query query = collection.whereEqualTo("name", name);

        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

        if(!documents.isEmpty()){
            return true;
        }
        return false;
    }


    public void addUser(UserDto userDto) {
        ApiFuture<com.google.cloud.firestore.WriteResult> apiFuture =
                db.collection(COLLECTION_NAME).document(userDto.getId()).set(userDto);
    }


}
