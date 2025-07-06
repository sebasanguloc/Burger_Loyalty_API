package org.api.burger_loyalty_api.dto;

import lombok.Data;

import java.util.*;

@Data
public class TargetRequestDto {

    private List<Long> idsTotalStamps;

    private List<Long> idsActiveStamps;

}
