package de.ait.bp.services.impl;

import de.ait.bp.dto.UserDto;
import de.ait.bp.handler.RestException;
import de.ait.bp.models.User;
import de.ait.bp.repositories.BookingsRepository;
import de.ait.bp.repositories.UsersRepository;
import de.ait.bp.services.UsersService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static de.ait.bp.dto.UserDto.from;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    UsersRepository usersRepository;
    BookingsRepository bookingsRepository;
    @Override
    public UserDto getUser(Long userId) {
        return from(getUserOrThrow(userId));
    }

    User getUserOrThrow(Long userId) {
        return usersRepository.findById(userId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "User with id <" + userId +"> not found"));
    }

    User getUsertOrThrow(Long personId) {
        return usersRepository.findById(personId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "User with id <" + personId +"> not found"));
    }
}
