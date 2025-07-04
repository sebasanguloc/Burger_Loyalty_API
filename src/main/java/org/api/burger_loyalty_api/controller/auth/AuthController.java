package org.api.burger_loyalty_api.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.api.burger_loyalty_api.dto.UserDto;
import org.api.burger_loyalty_api.dto.UserRegisterDto;
import org.api.burger_loyalty_api.model.User;
import org.api.burger_loyalty_api.repository.IUserRepository;
import org.api.burger_loyalty_api.service.inteface.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;

    @RequestMapping("/Angus-Burgers-Loyalty")
    public User AngusBurgersLoyalty(Authentication authentication){
        //User user = userRepository.findByMobileNumber(authentication.getName()).orElseThrow(() ->
        //        new UsernameNotFoundException("User details not found for user: " + authentication.getName()));
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto){
        UserDto newUser = userService.registerUser(userRegisterDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }


}
