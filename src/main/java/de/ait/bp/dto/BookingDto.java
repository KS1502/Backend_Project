package de.ait.bp.dto;

import de.ait.bp.models.Apartment;
import de.ait.bp.models.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDto {

    private Long id;

    private String checkInDate;

    private String checkOutDate;

    private double bookingPrice;

    private Booking.BookingStatus status;

    private String description;

    private Long apartmentId;

    public static BookingDto from(Booking booking) {
        BookingDto result = BookingDto.builder()
                .id(booking.getId())
                .checkInDate(booking.getCheckInDate().toString())
                .checkOutDate(booking.getCheckOutDate().toString())
                .bookingPrice(booking.getBookingPrice())
                .status(booking.getStatus())
                .description(booking.getDescription())
                .build();

     /*   if (booking.getCheckInDate() != null) {
            result.setCheckInDate(booking.getCheckInDate().toString());
        }

        if (booking.getCheckOutDate() != null) {
            result.setCheckOutDate(booking.getCheckOutDate().toString());
        } */
        if(booking.getApartment() != null) {
            result.setApartmentId(booking.getApartment().getId());
        }

        return result;
    }

    public static List<BookingDto> from(List<Booking> bookings) {
        return bookings.stream()
                .map(BookingDto::from)
                .collect(Collectors.toList());
    }
}
