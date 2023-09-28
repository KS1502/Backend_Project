package de.ait.bp.services.impl;

import de.ait.bp.dto.*;
import de.ait.bp.handler.RestException;
import de.ait.bp.models.Apartment;
import de.ait.bp.models.Booking;
import de.ait.bp.models.User;
import de.ait.bp.repositories.ApartmentsRepository;
import de.ait.bp.repositories.BookingsRepository;
import de.ait.bp.repositories.UsersRepository;
import de.ait.bp.services.ApartmentsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.stream.Collectors;

import static de.ait.bp.dto.BookingDto.from;
import static de.ait.bp.dto.ApartmentDto.from;
import static de.ait.bp.dto.UserDto.from;

@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Service
public class ApartmentsServiceImpl  implements ApartmentsService {

    ApartmentsRepository apartmentsRepository;

    UsersRepository usersRepository;

    UsersServiceImpl usersService;

    BookingsRepository bookingsRepository;

    @Transactional
    @Override
    public ApartmentDto addApartment(NewApartmentDto newApartment) {
        Apartment apartment = Apartment.builder()
                .numberOfRooms(newApartment.getNumberOfRooms())
                .type(newApartment.getType())
                .address(newApartment.getAddress())
                .description(newApartment.getDescription())
                .state(newApartment.getState())
                .build();

        apartmentsRepository.save(apartment);

        return from(apartment);
    }



    @Transactional
    @Override
    public ApartmentDto updateApartment(Long apartmentId, UpdateApartmentDto updateApartment) {

        Apartment apartment = getApartmentOrThrow(apartmentId);

        apartment.setDescription(updateApartment.getDescription());

        apartmentsRepository.save(apartment);

        return from(apartment);
    }

    @Override
    public ApartmentDto getApartment(Long apartmentId) {
        return from(getApartmentOrThrow(apartmentId));
    }

    @Override
    public PersonsDto addPersonToApartment(Long apartmentId, PersonForApartmentDto personId) {
        Apartment apartment = getApartmentOrThrow(apartmentId);

        User person = usersService.getUsertOrThrow(personId.getPersonId());

        person.getApartments().add(apartment);

        usersRepository.save(person);

        return getPersons(apartment);
    }
    private static PersonsDto getPersons(Apartment apartment) {
        return PersonsDto.builder()
                .persons(from(apartment.getPersons().stream()
                        .sorted(Comparator.comparing(User::getId))
                        .collect(Collectors.toList())))
                .build();
    }

    @Override
    public PersonsDto getPersonsOfApartment(Long apartmentId) {
        Apartment apartment = getApartmentOrThrow(apartmentId);

        return getPersons(apartment);


    }

    @Transactional
    @Override
    public BookingsDto addBookingToApartment(Long apartmentId, BookingForApartmentDto booking) {
        Booking bookingForApartment = bookingsRepository.findById(
                booking.getBookingId()).orElseThrow(() ->
                new RestException(HttpStatus.NOT_FOUND, "Booking with id <" + booking.getBookingId() + "> not found"));

        Apartment apartment = getApartmentOrThrow(apartmentId);

        bookingForApartment.setApartment(apartment);

        bookingsRepository.save(bookingForApartment);

        apartment.getBookings().add(bookingForApartment);

        return BookingsDto.builder()
                .bookings(from(apartment.getBookings()))
                .build();
    }

    @Override
    public BookingsDto getBookingsOfApartment(Long apartmentId) {
        Apartment apartment = getBookingOrThrow(apartmentId);

        return BookingsDto.builder()
                .bookings(from(apartment.getBookings()))
                .build();
    }

    Apartment getBookingOrThrow(Long apartmentId) {
        return apartmentsRepository.findById(apartmentId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "Apartment with id <" + apartmentId + "> not found"));

    }

    Apartment getApartmentOrThrow(Long apartmentId) {
        return apartmentsRepository.findById(apartmentId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "Apartment with id <" + apartmentId + "> not found"));
    }

    @Transactional
    @Override
    public ApartmentDto deleteApartment(Long apartmentId) {
        Apartment apartment = getApartmentOrThrow(apartmentId);

        apartment.setState(Apartment.State.DELETED);

        apartmentsRepository.save(apartment);

        logoutIfNecessary(apartmentId);

        return from(apartment);
    }

    private void logoutIfNecessary(Long userIdForLogout) {

        UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication());

        User currentUser = usersRepository.findByEmail(token.getName()).orElseThrow();

        if (currentUser.getId().equals(userIdForLogout)) {

            SecurityContextHolder.getContext().setAuthentication(null);
        }
    }


}
