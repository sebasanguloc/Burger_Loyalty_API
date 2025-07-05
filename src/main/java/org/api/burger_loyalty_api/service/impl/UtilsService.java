package org.api.burger_loyalty_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.api.burger_loyalty_api.dto.ActiveStampDto;
import org.api.burger_loyalty_api.dto.TotalStampDto;
import org.api.burger_loyalty_api.dto.UserTargetDto;
import org.api.burger_loyalty_api.repository.IUserRepository;
import org.api.burger_loyalty_api.service.inteface.IUtilsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilsService implements IUtilsService {

    private final IUserRepository userRepository;

    public Long findIdUserByMobileNumber(String mobileNumber){
        Long userId = userRepository.findIdByMobileNumber(mobileNumber).orElseThrow(() ->
                new UsernameNotFoundException("User not found by mobile number: " + mobileNumber));
        return userId;
    }

    @Override
    public UserTargetDto findUserTarget(String mobileNumber) {
        this.findIdUserByMobileNumber(mobileNumber);
        UserTargetDto userTargetDto = userRepository.findClientTotalStampsActiveStamps(mobileNumber);
        List<TotalStampDto> totalStampDtoList = userRepository.findAllStampsByMobileNumber(mobileNumber);
        List<ActiveStampDto> activeStampDtoList = userRepository.findAllActiveStampsByMobileNumber(mobileNumber);

        userTargetDto.setStamps(totalStampDtoList);
        userTargetDto.setActiveStamps(activeStampDtoList);

        return userTargetDto;
    }

}
