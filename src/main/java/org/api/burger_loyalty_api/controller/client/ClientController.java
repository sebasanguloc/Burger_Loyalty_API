package org.api.burger_loyalty_api.controller.client;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping
    public ResponseEntity<?> getClient(Authentication authentication){
        return null;
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PatchMapping("/claim")
    public ResponseEntity<?> claimStamp(Authentication authentication){
        return null;
    }

}
