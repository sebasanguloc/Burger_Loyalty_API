package org.api.burger_loyalty_api.service.inteface;

import org.api.burger_loyalty_api.dto.TotalStampDto;
import org.api.burger_loyalty_api.dto.UserTargetDto;

import java.util.List;

public interface ITotalStampService {

    void removeStampsByIds(List<Long> ids);

}
