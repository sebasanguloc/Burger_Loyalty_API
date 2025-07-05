package org.api.burger_loyalty_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.api.burger_loyalty_api.dto.ActiveStampDto;
import org.api.burger_loyalty_api.dto.TotalStampDto;
import org.api.burger_loyalty_api.dto.UserTargetDto;
import org.api.burger_loyalty_api.repository.IActiveStampRepository;
import org.api.burger_loyalty_api.repository.ITotalStampRepository;
import org.api.burger_loyalty_api.repository.IUserRepository;
import org.api.burger_loyalty_api.service.inteface.IActiveStampService;
import org.api.burger_loyalty_api.service.inteface.ITotalStampService;
import org.api.burger_loyalty_api.service.inteface.IUtilsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilsService implements IUtilsService {

    private final IUserRepository userRepository;
    private final IActiveStampRepository activeStampRepository;
    private final ITotalStampRepository totalStampRepository;

    public Long findIdUserByMobileNumber(String mobileNumber){
        Long userId = userRepository.findIdByMobileNumber(mobileNumber).orElseThrow(() ->
                new UsernameNotFoundException("User not found by mobile number: " + mobileNumber));
        return userId;
    }

    @Transactional
    @Override
    public UserTargetDto findUserTarget(String mobileNumber) {
        this.findIdUserByMobileNumber(mobileNumber);
        List<TotalStampDto> totalStampDtos = totalStampRepository.findAllStampsByMobileNumber(mobileNumber);
        List<ActiveStampDto> activeStampDtos = activeStampRepository.findAllActiveStampsByMobileNumber(mobileNumber);

        List<Long> idsToRemove = new ArrayList<>();
        List<ActiveStampDto> activeStampDtosFilter = activeStampDtos
                .stream()
                .filter(activeStampDto -> {
                    LocalDateTime today = LocalDateTime.now();
                    if(activeStampDto.getExpirationDt().isAfter(today) ||
                            activeStampDto.getExpirationDt().isEqual(today)){
                        return true;
                    }
                    idsToRemove.add(activeStampDto.getId());
                    return false;
                }).toList();

        activeStampRepository.removeActiveStampsByIds(idsToRemove);
        UserTargetDto userTargetDto = userRepository.findClientTotalStampsActiveStamps(mobileNumber);
        userTargetDto.setStamps(totalStampDtos);
        userTargetDto.setActiveStamps(activeStampDtosFilter);

        return userTargetDto;
    }



}
