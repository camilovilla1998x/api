package com.acs.seguridad.api.Service;

import com.acs.seguridad.api.enums.UserTypeEnum;
import com.acs.seguridad.api.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImp implements UserService{

    public List<User> get() {
        return users;
    }



    private static List<User> users = new ArrayList<>();


    //
    public UserServiceImp(List<User> usersInjected) {
        users = usersInjected;
    }

    //For get an user type
    public User getByUserType(String userType) {
        Optional<User> optionalUser = users.stream()
                .filter(users -> users.getUserType().equals(userType))
                .findFirst();

        return optionalUser.orElse(null);
    }


    public User getByIdUser(String idUser){
        Optional<User> optionalUser = users.stream()
                .filter(users -> users.getId().equals(idUser))
                .findFirst();

        return optionalUser.orElse(null);
    }

    //When we need to get a document number
    public User getByDocumentNumber(int DocumentNumber) {
        Optional<User> optionalUser = users.stream()
                .filter(users -> users.getDocumentNumber() == DocumentNumber)
                .findFirst();

        return optionalUser.orElse(null);
    }

    //Create an user
    public User create(User user) {
        /*if(user.getUserType() == "ADMIN"){
            Optional<User> optionalUser = users.stream()
                    .filter(users -> users.getUserType().equals(user))
                    .findFirst();
            return optionalUser.orElse(null);
        }*/
        user.setId(UUID.randomUUID().toString());
        users.add(user);

        return user;
    }



    public User update(String idUser, User user) {
        User oldUser = getByIdUser(idUser);

        if(Objects.isNull(oldUser)){
            return null;
        }

        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setDocumentType(user.getDocumentType());
        oldUser.setDocumentNumber(user.getDocumentNumber());

        return  oldUser;

    }



   /* public boolean delete(String idUser, String idShoppingCart) {
        boolean deleted = false;
        return idShoppingCart.equals(' ');
    }*/

    public boolean delete(String idUser, String idShoppingCart) {
        User user = getByIdUser(idUser);

        if (Objects.isNull(user)) {
            return true;
        }

        return users.removeIf(users -> users.getId().equals(idUser));
    }



}
