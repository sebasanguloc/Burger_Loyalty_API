package org.api.burger_loyalty_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.api.burger_loyalty_api.model.ActiveStamp;
import org.api.burger_loyalty_api.model.User;
import org.api.burger_loyalty_api.repository.IActiveStampRepository;
import org.api.burger_loyalty_api.service.inteface.IActiveStampService;
import org.api.burger_loyalty_api.service.inteface.IUtilsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ActiveStampService implements IActiveStampService {

    private final IActiveStampRepository activeStampRepository;
    private final IUtilsService utilsService;

    @Override
    public void activeStamp(String mobileNumber) {
        Long id = utilsService.findIdUserByMobileNumber(mobileNumber);
        User user = new User();
        user.setId(id);

        ActiveStamp activeStamp = new ActiveStamp();
        activeStamp.setUser(user);
        activeStamp.setExpirationDt(LocalDateTime.now().plusHours(24));

        activeStampRepository.save(activeStamp);
    }
}
