package org.api.burger_loyalty_api.service.inteface;

import org.api.burger_loyalty_api.dto.TotalStampDto;
import org.api.burger_loyalty_api.dto.UserTargetDto;

import java.util.List;

public interface ITotalStampService {

    void addStampToTarget(String mobileNumber, long totalStamps, List<Long> idsToActive);

    void removeTotalStampsByIds(String mobileNumber, List<Long> ids);
}
