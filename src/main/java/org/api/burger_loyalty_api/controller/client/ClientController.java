package org.api.burger_loyalty_api.controller.client;

import lombok.RequiredArgsConstructor;
import org.api.burger_loyalty_api.dto.UserTargetDto;
import org.api.burger_loyalty_api.service.inteface.IActiveStampService;
import org.api.burger_loyalty_api.service.inteface.ITotalStampService;
import org.api.burger_loyalty_api.service.inteface.IUserService;
import org.api.burger_loyalty_api.service.inteface.IUtilsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final IUserService userService;
    private final IActiveStampService activeStampService;
    private final ITotalStampService totalStampService;
    private final IUtilsService utilsService;

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping
    public ResponseEntity<UserTargetDto> getClient(Authentication authentication){
        String mobileNumber = authentication.getName();
        UserTargetDto userTarget = utilsService.findUserTarget(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(userTarget);
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PatchMapping("/claim")
    public ResponseEntity<?> claimStamp(Authentication authentication){
        return null;
    }

}
