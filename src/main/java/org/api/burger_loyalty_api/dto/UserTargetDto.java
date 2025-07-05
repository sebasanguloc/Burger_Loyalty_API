package org.api.burger_loyalty_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class UserTargetDto {

    private String name;

    private String mobileNumber;

    private Long totalStamps;

    private List<TotalStampDto> stamps;

    private Long totalActiveStamps;

    private List<ActiveStampDto> activeStamps;

}
