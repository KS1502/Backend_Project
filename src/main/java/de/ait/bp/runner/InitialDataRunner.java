package de.ait.bp.runner;

import de.ait.bp.models.User;
import de.ait.bp.repositories.UsersRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
@Component
@Profile("!test")
public class InitialDataRunner implements CommandLineRunner {

    UsersRepository usersRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        log.info("InitialDataRunner start...");

        if (!usersRepository.existsByRole(User.Role.ADMIN)) {
            User admin = User.builder()
                    .email("admin@gmail.com")
                    .state(User.State.CONFIRMED)
                    .role(User.Role.ADMIN)
                    .hashPassword("$2a$10$vhP3Ca6hm0QtXK2jrlVOQ.tReiadb3scp24dakQA6CUHo4oG7cXNO")
                    .build();

            usersRepository.save(admin);

            log.info("Insert admin into database");
        }

        if (usersRepository.count() == 1) {
            User example = User.builder()
                    .email("example@gmail.com")
                    .state(User.State.CONFIRMED)
                    .role(User.Role.GUEST)
                    .hashPassword("$2a$10$rw/mE1Z.Lc/KxAAJ7cB.TOi0p2zYdHirOg2Sybl/IQy9JHyDXeqXC")
                    .build();

            log.info("Insert example into database");

            User manager = User.builder()
                    .email("manager@gmail.com")
                    .state(User.State.CONFIRMED)
                    .role(User.Role.MANAGER)
                    .hashPassword("$2a$10$5aCLBcb9jmoLqJHvglsgvOqcGeTqJUAtNn4SwBc1hVjQA9JwgicOy")
                    .build();

            usersRepository.save(example);
            usersRepository.save(manager);


            log.info("Insert manager into database");
        }
    }
}
