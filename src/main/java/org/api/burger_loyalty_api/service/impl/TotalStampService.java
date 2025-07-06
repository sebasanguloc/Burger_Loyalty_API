package org.api.burger_loyalty_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.api.burger_loyalty_api.model.TotalStamp;
import org.api.burger_loyalty_api.model.User;
import org.api.burger_loyalty_api.repository.ITotalStampRepository;
import org.api.burger_loyalty_api.service.inteface.IActiveStampService;
import org.api.burger_loyalty_api.service.inteface.ITotalStampService;
import org.api.burger_loyalty_api.service.inteface.IUtilsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TotalStampService implements ITotalStampService {

    private final ITotalStampRepository totalStampRepository;
    private final IActiveStampService activeStampService;
    private final IUtilsService utilsService;

    @Override
    public void addStampToTarget(String mobileNumber, long totalStamps, List<Long> idsToActive) {
        Long idUser = utilsService.findIdUserByMobileNumber(mobileNumber);
        User user = new User();
        user.setId(idUser);

        List<Long> stampsActivated = new ArrayList<>();
        long toAdd = 0;
        for(Long id : idsToActive){
            totalStamps++;
            if(totalStamps > 12){
                totalStamps = 0;
                totalStampRepository.removeStampsByMobileNumber(mobileNumber);
            }else if(totalStamps == 6 || totalStamps == 12){
                toAdd++;
                stampsActivated.add(id);
                break;
            }
            toAdd++;
            stampsActivated.add(id);
        }
        for (int i = 0; i < toAdd; i++) {
            TotalStamp stamp = new TotalStamp();
            stamp.setUser(user);
            totalStampRepository.save(stamp);
        }
        activeStampService.removeActiveStampsByIds(mobileNumber, stampsActivated);
    }

    @Transactional
    @Override
    public void removeTotalStampsByIds(String mobileNumber, List<Long> ids) {
        utilsService.findIdUserByMobileNumber(mobileNumber);
        totalStampRepository.removeTotalStampsByIds(ids);
    }

}
