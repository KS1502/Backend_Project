package de.ait.bp.services;


import de.ait.bp.dto.BookingDto;
import de.ait.bp.dto.NewBookingDto;
import de.ait.bp.dto.UpdateBookingDto;


public interface BookingsService {

    BookingDto addBooking(NewBookingDto booking);

    BookingDto deleteBooking(Long bookingId);

    BookingDto getBooking(Long bookingId);

    BookingDto updateBooking(Long bookingId, UpdateBookingDto updateBooking);
}
