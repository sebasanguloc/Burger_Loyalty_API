package org.api.burger_loyalty_api.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.api.burger_loyalty_api.model.User;
import org.api.burger_loyalty_api.repository.IUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUserRepository userRepository;

    @RequestMapping("/Angus-Burgers-Loyalty")
    public User AngusBurgersLoyalty(Authentication authentication){
        User user = userRepository.findByMobileNumber(authentication.getName()).orElseThrow(() ->
                new UsernameNotFoundException("User details not found for user: " + authentication.getName()));
        return user;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(/*@Valid @RequestBody User user*/){
        return null;
    }


}
