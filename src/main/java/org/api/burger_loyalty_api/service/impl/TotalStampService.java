package org.api.burger_loyalty_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.api.burger_loyalty_api.dto.TotalStampDto;
import org.api.burger_loyalty_api.repository.ITotalStampRepository;
import org.api.burger_loyalty_api.service.inteface.ITotalStampService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TotalStampService implements ITotalStampService {

    private final ITotalStampRepository totalStampRepository;

    @Override
    public void removeStampsByIds(List<Long> ids) {

    }

}
