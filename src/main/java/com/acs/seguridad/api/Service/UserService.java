package com.acs.seguridad.api.Service;


import com.acs.seguridad.api.model.User;

import java.util.List;

public interface UserService {

    List<User> get();
    User getByUserType(String userType);


    User getByDocumentNumber(int DocumentNumber);

    User getByIdUser(String idUser);

    User create(User user);

    User update(String idUser, User user);

    boolean delete(String idUser, String idShoppingCart);

    //boolean delete1(String idUser);


}
