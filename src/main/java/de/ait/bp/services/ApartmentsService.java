package de.ait.bp.services;

import de.ait.bp.dto.*;

public interface ApartmentsService {
   ApartmentDto addApartment(NewApartmentDto newApartment);

    ApartmentDto updateApartment(Long apartmentId, UpdateApartmentDto updateApartment);

    ApartmentDto deleteApartment(Long apartmentId);

    ApartmentDto getApartment(Long apartmentId);


    PersonsDto addPersonToApartment(Long apartmentId, PersonForApartmentDto person);

    PersonsDto getPersonsOfApartment(Long apartmentId);

    BookingsDto addBookingToApartment(Long apartmentId, BookingForApartmentDto booking);

    BookingsDto getBookingsOfApartment(Long apartmentId);
}

