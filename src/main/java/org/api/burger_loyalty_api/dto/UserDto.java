package org.api.burger_loyalty_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 10)
    private String mobileNumber;

    @NotBlank
    @Email
    @Size(max = 100)
    private String email;

    private LocalDateTime createDt;
}
