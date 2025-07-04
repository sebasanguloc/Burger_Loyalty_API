package org.api.burger_loyalty_api.controller.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class AdminController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAllClients(){
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{mobileNumber}")
    public ResponseEntity<?> getClient(@PathVariable String mobileNumber){
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/active/{mobileNumber}")
    public ResponseEntity<?> activeButton(@PathVariable String mobileNumber){
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{mobileNumber}")
    public ResponseEntity<?> deleteClient(@PathVariable String mobileNumber){
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{mobileNumber}")
    public ResponseEntity<?> updateClient(@PathVariable String mobileNumber){
        return null;
    }


}
