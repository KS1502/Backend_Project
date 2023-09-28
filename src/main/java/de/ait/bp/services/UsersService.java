package de.ait.bp.services;

import de.ait.bp.dto.UserDto;

public interface UsersService {
    UserDto getUser(Long userId);
}
