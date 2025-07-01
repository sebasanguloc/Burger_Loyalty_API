package org.api.burger_loyalty_api.controller.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @GetMapping("/{mobileNumber}")
    public ResponseEntity<?> getClient(@PathVariable String mobileNumber){
        return null;
    }

    @PatchMapping("/claim/{mobileNumber}")
    public ResponseEntity<?> claimStamp(){
        return null;
    }

}
