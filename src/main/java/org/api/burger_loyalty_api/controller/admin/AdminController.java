package org.api.burger_loyalty_api.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class AdminController {

    @GetMapping
    public ResponseEntity<?> getAllClients(){
        return null;
    }

    @GetMapping("/{mobileNumber}")
    public ResponseEntity<?> getClient(@PathVariable String mobileNumber){
        return null;
    }

    @PatchMapping("/active/{mobileNumber}")
    public ResponseEntity<?> activeButton(@PathVariable String mobileNumber){
        return null;
    }

    @PostMapping("/{mobileNumber}")
    public ResponseEntity<?> deleteClient(@PathVariable String mobileNumber){
        return null;
    }

    @PutMapping("/{mobileNumber}")
    public ResponseEntity<?> updateClient(@PathVariable String mobileNumber){
        return null;
    }


}
