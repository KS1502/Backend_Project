package de.ait.bp.controllers;

import de.ait.bp.controllers.api.BookingsApi;
import de.ait.bp.dto.*;
import de.ait.bp.dto.UpdateBookingDto;
import de.ait.bp.services.BookingsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
public class BookingsController implements BookingsApi {

    BookingsService bookingsService;

    @Override
    public ResponseEntity<BookingDto> addBooking(NewBookingDto booking) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookingsService.addBooking(booking));
    }

    @Override
    public ResponseEntity<BookingDto> updateBooking(Long bookingId, UpdateBookingDto updateBooking ) {
        return ResponseEntity
                .ok()
                .body(bookingsService.updateBooking(bookingId, updateBooking));
    }

    @Override
    public ResponseEntity<BookingDto> deleteBooking(Long bookingId) {
        return ResponseEntity.ok(bookingsService.deleteBooking(bookingId));
    }

    @Override
    public ResponseEntity<BookingDto> getBooking(Long bookingId) {
        return ResponseEntity
                .ok()
                .body(bookingsService.getBooking(bookingId));
    }

    @Override
    public ResponseEntity<BookingDto> getBooking() {
        return null;
    }


}
