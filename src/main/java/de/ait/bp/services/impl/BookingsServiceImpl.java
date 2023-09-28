package de.ait.bp.services.impl;

import de.ait.bp.dto.BookingDto;
import de.ait.bp.dto.NewBookingDto;
import de.ait.bp.dto.UpdateBookingDto;
import de.ait.bp.handler.RestException;
import de.ait.bp.models.Booking;
import de.ait.bp.models.User;
import de.ait.bp.repositories.ApartmentsRepository;
import de.ait.bp.repositories.BookingsRepository;
import de.ait.bp.repositories.UsersRepository;
import de.ait.bp.services.BookingsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static de.ait.bp.dto.BookingDto.from;

@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Service
public class BookingsServiceImpl implements BookingsService {

    BookingsRepository bookingsRepository;
    ApartmentsRepository apartmentsRepository;
    UsersRepository usersRepository;

    @Transactional
    @Override
    public BookingDto addBooking(NewBookingDto booking) {
        Booking newBooking = Booking.builder()
                .checkInDate(LocalDate.parse(booking.getCheckInDate().toString()))
                .checkOutDate(LocalDate.parse(booking.getCheckOutDate().toString()))
                .bookingPrice(booking.getBookingPrice())
                .status(booking.getStatus())
                .description(booking.getDescription())
                .build();

        bookingsRepository.save(newBooking);

        return from(newBooking);
    }

    @Transactional
    @Override
    public BookingDto updateBooking(Long bookingId, UpdateBookingDto updateBooking) {

        Booking booking = getBookingOrThrow(bookingId);

        booking.setDescription(updateBooking.getDescription());

        if (updateBooking.getCheckInDate() != null) {
            booking.setCheckInDate(LocalDate.parse(updateBooking.getCheckInDate()));
        }

        if (updateBooking.getCheckOutDate() != null) {
            booking.setCheckOutDate(LocalDate.parse(updateBooking.getCheckOutDate()));
        }

        bookingsRepository.save(booking);

        return from(booking);
    }


    @Transactional
    @Override
    public BookingDto deleteBooking(Long bookingId) {
        Booking booking = getBookingOrThrow(bookingId);

        booking.setStatus(Booking.BookingStatus.CANCELED);

        bookingsRepository.save(booking);

        logoutIfNecessary(bookingId);

        return BookingDto.from(booking);
    }

    @Override
    public BookingDto getBooking(Long bookingId) {
        return BookingDto.from(getBookingOrThrow(bookingId));
    }




    Booking getBookingOrThrow(Long bookingId) {
        return bookingsRepository.findById(bookingId).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, "Apartment with id <" + bookingId + "> not found"));
    }

    private void logoutIfNecessary(Long userIdForLogout) {

        UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication());

        User currentUser = usersRepository.findByEmail(token.getName()).orElseThrow();

        if (currentUser.getId().equals(userIdForLogout)) {

            SecurityContextHolder.getContext().setAuthentication(null);
        }
    }
}
