package org.api.burger_loyalty_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.api.burger_loyalty_api.repository.ITotalStampRepository;
import org.api.burger_loyalty_api.service.inteface.ITotalStampService;
import org.api.burger_loyalty_api.service.inteface.IUtilsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TotalStampService implements ITotalStampService {

    private final ITotalStampRepository totalStampRepository;
    private final IUtilsService utilsService;

    @Transactional
    @Override
    public void removeTotalStampsByIds(String mobileNumber, List<Long> ids) {
        utilsService.findIdUserByMobileNumber(mobileNumber);
        totalStampRepository.removeTotalStampsByIds(ids);
    }

}
