package de.ait.bp.controllers.api;

import de.ait.bp.dto.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/apartments")
@Tags(value =
@Tag(name = "Apartments")
)
public interface ApartmentsApi {

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @PostMapping
    ResponseEntity<ApartmentDto> addApartment(@RequestBody @Valid NewApartmentDto newApartment);

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    @PutMapping("/{apartment-id}")
    ResponseEntity<ApartmentDto> updateApartment(@PathVariable("apartment-id") Long apartmentId,
                                           @RequestBody @Valid UpdateApartmentDto updateApartment);

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{apartment-id}")
    ResponseEntity<ApartmentDto> deleteApartment(@PathVariable("apartment-id") Long apartmentId);

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{apartment-id}")
    ResponseEntity<ApartmentDto> getApartment(@PathVariable("apartment-id") Long apartmentId);

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    ResponseEntity<ApartmentDto> getApartment();

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/{apartment-id}/persons")
    ResponseEntity<PersonsDto> addPersonToApartment(@PathVariable("apartment-id") Long apartmentId,
                                                     @RequestBody @Valid PersonForApartmentDto person);

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/{apartment-id}/persons")
    ResponseEntity<PersonsDto> getPersonsOfApartment(@PathVariable("apartment-id") Long apartmentId);

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/{apartment-id}/bookings")
    ResponseEntity<BookingsDto> addBookingToApartment(@PathVariable("apartment-id") Long apartmentId,
                                                     @RequestBody @Valid BookingForApartmentDto booking);

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/{apartment-id}/bookings")
    ResponseEntity<BookingsDto> getBookingsOfApartment(@PathVariable("apartment-id") Long apartmentId);




}
