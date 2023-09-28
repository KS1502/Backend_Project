package de.ait.bp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class RegisterDto {

    @Schema(description = "User email", example = "example@gmail.com")
    @Email
    @NotNull
    @NotBlank
    private String email;

    @Schema(description = "User Password", example = "Qwerty123!")
    @NotBlank
    @Size(min = 7, max = 30)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "Week password. The password must be at least 7 characters long and no more than 30, " +
                    "containing both uppercase and lowercase letters," +
                    "digits, and at least 1 special character such as ! or ? etc.")
    private String password;

}
