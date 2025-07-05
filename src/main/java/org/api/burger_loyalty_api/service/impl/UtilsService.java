package org.api.burger_loyalty_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.api.burger_loyalty_api.repository.IUserRepository;
import org.api.burger_loyalty_api.service.inteface.IUtilsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtilsService implements IUtilsService {

    private final IUserRepository userRepository;

    public Long findIdUserByMobileNumber(String mobileNumber){
        Long userId = userRepository.findIdByMobileNumber(mobileNumber).orElseThrow(() ->
                new UsernameNotFoundException("User not found by mobile number: " + mobileNumber));
        return userId;
    }

}
