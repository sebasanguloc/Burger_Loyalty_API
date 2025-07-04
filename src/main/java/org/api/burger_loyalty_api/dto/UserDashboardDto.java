package org.api.burger_loyalty_api.dto;

import lombok.Data;

@Data
public class UserDashboardDto {

    private String name;

    private String mobileNumber;

    private Long totalStamps;

    public UserDashboardDto(String name, String mobileNumber, Long totalStamps) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.totalStamps = totalStamps;
    }
}
