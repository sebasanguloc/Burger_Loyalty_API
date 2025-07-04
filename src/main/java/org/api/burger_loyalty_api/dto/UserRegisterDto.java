package org.api.burger_loyalty_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.api.burger_loyalty_api.validation.anotation.PasswordMatches;

@Data
@AllArgsConstructor
@PasswordMatches
public class UserRegisterDto {

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 10, max = 10, message = "The mobile number must be exactly 10 digits long")
    @Pattern(regexp = "\\d{10}", message = "The mobile number must contain digits only")
    private String mobileNumber;

    @NotBlank
    @Email(message = "Invalid email")
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotBlank
    @Size(min = 8)
    private String repeatPassword;

}
