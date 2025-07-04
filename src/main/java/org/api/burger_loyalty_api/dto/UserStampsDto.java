package org.api.burger_loyalty_api.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserStampsDto {

    private String name;

    private String mobileNumber;

    private Long totalActiveStamps;

    private List<LocalDateTime> stamps;



}
