package de.ait.bp.services;

import de.ait.bp.dto.RegisterDto;
import de.ait.bp.dto.UserDto;


public interface RegistrationService {
    UserDto register(RegisterDto registerData);
}
