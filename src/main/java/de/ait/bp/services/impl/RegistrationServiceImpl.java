package de.ait.bp.services.impl;

import de.ait.bp.dto.RegisterDto;
import de.ait.bp.dto.UserDto;
import de.ait.bp.models.User;
import de.ait.bp.repositories.UsersRepository;
import de.ait.bp.services.RegistrationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static de.ait.bp.dto.UserDto.from;


@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Service
public class RegistrationServiceImpl implements RegistrationService {

    UsersRepository usersRepository;

    PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDto register(RegisterDto registerData) {
        User user = User.builder()
                .email(registerData.getEmail())
                .hashPassword(passwordEncoder.encode(registerData.getPassword()))
                .role(User.Role.GUEST)
                .state(User.State.CONFIRMED).build();

        usersRepository.save(user);

        return from(user);
    }
}
