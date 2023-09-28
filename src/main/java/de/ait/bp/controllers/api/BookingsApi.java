package de.ait.bp.controllers.api;

import de.ait.bp.dto.*;
import de.ait.bp.dto.UpdateBookingDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/bookings")
@Tags(value =
@Tag(name = "Bookings")
)
public interface BookingsApi {

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @PostMapping
    ResponseEntity<BookingDto> addBooking(@RequestBody @Valid NewBookingDto booking);

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PutMapping("/{booking-id}")
    ResponseEntity<BookingDto> updateBooking(@PathVariable("booking-id") Long bookingId,
                                            @RequestBody @Valid UpdateBookingDto updateBooking);

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{booking-id}")
    ResponseEntity<BookingDto> deleteBooking(@PathVariable("booking-id") Long bookingId);

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{booking-id}")
    ResponseEntity<BookingDto> getBooking(@PathVariable("booking-id") Long bookingId);

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    ResponseEntity<BookingDto> getBooking();
}
