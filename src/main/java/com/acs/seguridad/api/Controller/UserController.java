package com.acs.seguridad.api.Controller;

import com.acs.seguridad.api.Service.UserService;
import com.acs.seguridad.api.model.User;
import com.acs.seguridad.api.enums.UserTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    UserTypeEnum userTypeEnum;



    //the list is created
     @GetMapping("users")
    public ResponseEntity<List<User>> get() {
        List<User> users = userService.get();

        return users.isEmpty() ? ResponseEntity.badRequest().body(users) : ResponseEntity.ok(users);
    }

    //Check the type of user
    @GetMapping("users/{userType}")
    public ResponseEntity<User> getByUserType(@PathVariable String userType) {
        User users = userService.getByUserType(userType);

        return Objects.isNull(users) ? ResponseEntity.notFound().build() : ResponseEntity.ok(users);
    }

    //consult the document number
    @GetMapping("users/{userType}/{documentNumber}")
    public ResponseEntity<User> getByDocumentNumber(@PathVariable int documentNumber) {
        User users = userService.getByDocumentNumber(documentNumber);

        return Objects.isNull(users) ? ResponseEntity.notFound().build() : ResponseEntity.ok(users);
    }

    //- The api should allow creating a user.
    //- Only an administrator user can create a user of type buyer and seller.
    /*@PostMapping("users")
    public ResponseEntity<User> create(@RequestBody User user) {
        if (user.getUserType().equals("ADMIN") || user.getUserType().equals("BUYER") || user.getUserType().equals("SELLER") ) {
            return ResponseEntity.ok(userService.create(user));
        }
        return null;
    }*/

    @PostMapping("users/{ADMIN}")
    public ResponseEntity<User> create(@RequestBody  User user) {
        if (user.getUserType().equals("SELLER") || user.getUserType().equals("BUYER")) {
            return ResponseEntity.ok(userService.create(user));
        }

        return null;
        /*if (user.getUserType().equals(userTypeEnum.ADMIN) || user.getUserType().equals(UserTypeEnum.BUYER) || user.getUserType().equals(userTypeEnum.SELLER)) {
            return ResponseEntity.ok(userService.create(user));
        }*/
    }


    //- Names, surnames, document type and document number can be edited.
    //@PutMapping("users/{firstName}/{LastName}/{documentType}/{documentNumber}")
    @PutMapping("users/{idUser}")
    public ResponseEntity<User> update(@PathVariable String idUser, @RequestBody User user) {
        User userUpdated = userService.update(idUser, user);

        return Objects.isNull(userUpdated) ? ResponseEntity.notFound().build() : ResponseEntity.ok(userUpdated);
    }


    //- A user will only be deleted if and only if he has no sales or products in an active cart.
    @DeleteMapping("users/{idUser}")
    public ResponseEntity delete(@PathVariable String idUser, String idShoppingCart) {
         if(idShoppingCart == null){
            boolean deleted = userService.delete(idUser, idShoppingCart );
            return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
         }
        return null;
    }

    //- A user will only be deleted if and only if he has no sales or products in an active cart.
   /* @DeleteMapping("users/{idUser}")
    public ResponseEntity delete(@PathVariable String idUser, String idShoppingCart) {
        boolean deleted = false;
        if (idShoppingCart.equals(' ')){
            deleted = userService.delete(idUser, idShoppingCart);
        }
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }*/

}
