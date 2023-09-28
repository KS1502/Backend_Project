package de.ait.bp.controllers;

import de.ait.bp.controllers.api.ApartmentsApi;
import de.ait.bp.dto.*;
import de.ait.bp.services.ApartmentsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
public class ApartmentsController implements ApartmentsApi {

    ApartmentsService apartmentsService;

    @Override
    public ResponseEntity<ApartmentDto> addApartment(NewApartmentDto newApartment) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(apartmentsService.addApartment(newApartment));
    }

    @Override
    public ResponseEntity<ApartmentDto> updateApartment(Long apartmentId, UpdateApartmentDto updateApartment ) {
        return ResponseEntity
                .ok()
                .body(apartmentsService.updateApartment(apartmentId, updateApartment));
    }

    @Override
    public ResponseEntity<ApartmentDto> deleteApartment(Long apartmentId) {
        return ResponseEntity.ok(apartmentsService.deleteApartment(apartmentId));
    }

    @Override
    public ResponseEntity<ApartmentDto> getApartment(Long apartmentId) {
        return ResponseEntity
                .ok()
                .body(apartmentsService.getApartment(apartmentId));
    }

    @Override
    public ResponseEntity<ApartmentDto> getApartment() {
        return null;
    }

    @Override
    public ResponseEntity<PersonsDto> addPersonToApartment(Long apartmentId, PersonForApartmentDto person) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(apartmentsService.addPersonToApartment(apartmentId, person));
    }

    @Override
    public ResponseEntity<PersonsDto> getPersonsOfApartment(Long apartmentId) {
        return ResponseEntity
                .ok()
                .body(apartmentsService.getPersonsOfApartment(apartmentId));
    }

    @Override
    public ResponseEntity<BookingsDto> addBookingToApartment(Long apartmentId, BookingForApartmentDto booking) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(apartmentsService.addBookingToApartment(apartmentId, booking));
    }

    @Override
    public ResponseEntity<BookingsDto> getBookingsOfApartment(Long apartmentId) {
        return ResponseEntity.ok()
                .body(apartmentsService.getBookingsOfApartment(apartmentId));
    }


}
