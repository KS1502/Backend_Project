package de.ait.bp.controllers;

import de.ait.bp.controllers.api.RegistrationApi;
import de.ait.bp.dto.RegisterDto;
import de.ait.bp.dto.UserDto;
import de.ait.bp.services.RegistrationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RestController
public class RegistrationController implements RegistrationApi {

    RegistrationService registrationService;

    @Override
    public ResponseEntity<UserDto> register(RegisterDto registerData) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registrationService.register(registerData));
    }
}
