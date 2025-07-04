package org.api.burger_loyalty_api.controller.client;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/{mobileNumber}")
    public ResponseEntity<?> getClient(@PathVariable String mobileNumber){
        return null;
    }

    @PreAuthorize("hasRole('CLIENT')")
    @PatchMapping("/claim/{mobileNumber}")
    public ResponseEntity<?> claimStamp(){
        return null;
    }

}
