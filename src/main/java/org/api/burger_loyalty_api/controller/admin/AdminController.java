package org.api.burger_loyalty_api.controller.admin;

import lombok.RequiredArgsConstructor;
import org.api.burger_loyalty_api.dto.ResponseDto;
import org.api.burger_loyalty_api.dto.UserDashboardDto;
import org.api.burger_loyalty_api.service.inteface.IActiveStampService;
import org.api.burger_loyalty_api.service.inteface.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class AdminController {

    private final IUserService userService;
    private final IActiveStampService activeStampService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<UserDashboardDto>> getAllClients(@PageableDefault(page = 0, size = 20) Pageable pageable){
        Page<UserDashboardDto> usersPage = userService.findAllClientsByPage(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(usersPage);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{mobileNumber}")
    public ResponseEntity<?> getClient(@PathVariable String mobileNumber){
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/active/{mobileNumber}")
    public ResponseEntity<ResponseDto> activeButton(@PathVariable String mobileNumber){
        activeStampService.activeStamp(mobileNumber);
        ResponseDto response = new ResponseDto(
                String.valueOf(HttpStatus.OK.value()),
                "Stamp activated to user: " + mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{mobileNumber}")
    public ResponseEntity<ResponseDto> removeClient(@PathVariable String mobileNumber){
        userService.removeUser(mobileNumber);
        ResponseDto response = new ResponseDto(
                String.valueOf(HttpStatus.OK.value()),
                "User delete successfully"
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{mobileNumber}")
    public ResponseEntity<?> updateClient(@PathVariable String mobileNumber){
        return null;
    }


}
