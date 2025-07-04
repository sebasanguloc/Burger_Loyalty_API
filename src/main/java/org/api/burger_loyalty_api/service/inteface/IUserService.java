package org.api.burger_loyalty_api.service.inteface;

import org.api.burger_loyalty_api.dto.UserDto;
import org.api.burger_loyalty_api.dto.UserRegisterDto;

public interface IUserService{

    UserDto registerUser(UserRegisterDto userRegisterDto);

}
