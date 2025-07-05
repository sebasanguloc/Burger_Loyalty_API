package org.api.burger_loyalty_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.api.burger_loyalty_api.dto.UserDashboardDto;
import org.api.burger_loyalty_api.dto.UserDto;
import org.api.burger_loyalty_api.dto.UserRegisterDto;
import org.api.burger_loyalty_api.exception.UserAlreadyExistException;
import org.api.burger_loyalty_api.mapper.UserMapper;
import org.api.burger_loyalty_api.model.Authority;
import org.api.burger_loyalty_api.model.User;
import org.api.burger_loyalty_api.repository.IAuthorityRepository;
import org.api.burger_loyalty_api.repository.IUserRepository;
import org.api.burger_loyalty_api.service.inteface.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IAuthorityRepository authorityRepository;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(UserRegisterDto userRegisterDto) {

        String hashPwd = passwordEncoder.encode(userRegisterDto.getPassword());
        Boolean userExist = userRepository.existsByMobileNumberOrEmail(
                userRegisterDto.getMobileNumber(), userRegisterDto.getEmail());

        if(userExist){
            throw new UserAlreadyExistException("User already exists with that Email or Mobile Number");
        }

        User user = new User();
        user.setName(userRegisterDto.getName());
        user.setEmail(userRegisterDto.getEmail().toLowerCase());
        user.setMobileNumber(userRegisterDto.getMobileNumber());
        user.setPassword(hashPwd);

        User newUser = userRepository.save(user);

        Authority authority = new Authority();
        authority.setName("ROLE_CLIENT");
        authority.setUser(newUser);

        authorityRepository.save(authority);

        return UserMapper.UserToUserDto(newUser);
    }

    @Override
    public Page<UserDashboardDto> findAllClientsByPage(Pageable pageable) {
        Page<UserDashboardDto> userDashboardDtoPage = userRepository.findAllClients(pageable);
        return userDashboardDtoPage;
    }

    @Override
    public void removeUser(String mobileNumber) {
        
    }


}
