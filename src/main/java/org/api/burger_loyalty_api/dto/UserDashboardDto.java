package org.api.burger_loyalty_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDashboardDto {

    private String name;

    private String mobileNumber;

    private Long totalStamps;

}
