package org.api.burger_loyalty_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ActiveStampDto {

    private Long id;

    private LocalDateTime enableDt;

    private LocalDateTime expirationDt;

}
