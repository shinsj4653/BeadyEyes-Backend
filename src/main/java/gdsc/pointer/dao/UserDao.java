package gdsc.pointer.dao;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import gdsc.pointer.domain.User;
import gdsc.pointer.dto.request.login.UserDto;
import gdsc.pointer.exception.notfound.user.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class UserDao {

    public static final String COLLECTION_NAME = "User";

    public List<User> getUsers() throws Exception {
        List<User> list = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            list.add(document.toObject(User.class));
        }
        return list;
    }


    public User getUserDetail(String id) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        User user = null;
        if(documentSnapshot.exists()){
            user = documentSnapshot.toObject(User.class);
        }
        return user;
    }

    public boolean isUserEmailExist(String email) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collection = firestore.collection(COLLECTION_NAME);
        // Create a query to check for the user with the specified email
        Query query = collection.whereEqualTo("email", email);

        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

        if(!documents.isEmpty()){
            return true;
        }
        return false;
    }

    public boolean isUserNameExist(String name) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collection = firestore.collection(COLLECTION_NAME);
        // Create a query to check for the user with the specified email
        Query query = collection.whereEqualTo("name", name);

        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

        if(!documents.isEmpty()){
            return true;
        }
        return false;
    }


    public void addUser(UserDto userDto) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<com.google.cloud.firestore.WriteResult> apiFuture =
                firestore.collection(COLLECTION_NAME).document(userDto.getId()).set(userDto);
    }


}
