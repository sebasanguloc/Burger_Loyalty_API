package org.api.burger_loyalty_api.mapper;

import org.api.burger_loyalty_api.dto.UserDto;
import org.api.burger_loyalty_api.model.User;

public class UserMapper {

    public static UserDto UserToUserDto(User user){
        UserDto userDto = new UserDto(
                user.getId(),
                user.getName(),
                user.getMobileNumber(),
                user.getEmail(),
                user.getCreateDt()
        );
        return userDto;
    }

}
