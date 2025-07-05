package org.api.burger_loyalty_api.service.inteface;

import org.api.burger_loyalty_api.dto.UserTargetDto;

public interface IUtilsService {

    Long findIdUserByMobileNumber(String mobileNumber);

    UserTargetDto findUserTarget(String mobileNumber);

}
