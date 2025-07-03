package org.api.burger_loyalty_api.controller.auth;

import jakarta.validation.Valid;
import org.api.burger_loyalty_api.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // /register
    // /forgot-password
    //

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(/*@Valid @RequestBody User user*/){
        return null;
    }


}
