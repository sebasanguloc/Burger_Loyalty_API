package org.api.burger_loyalty_api.service.inteface;

import org.api.burger_loyalty_api.dto.UserDashboardDto;
import org.api.burger_loyalty_api.dto.UserDto;
import org.api.burger_loyalty_api.dto.UserRegisterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService{

    UserDto registerUser(UserRegisterDto userRegisterDto);

    Page<UserDashboardDto> findAllClientsByPage(Pageable pageable);

}
