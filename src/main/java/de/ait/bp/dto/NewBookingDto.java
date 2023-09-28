package de.ait.bp.dto;

import de.ait.bp.models.Apartment;
import de.ait.bp.models.Booking;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
public class NewBookingDto {

    @Schema(name = "checkInDate", example = "yyyy-mm-dd")
    private LocalDate checkInDate;

    @Schema(name = "checkOutDate", example = "yyyy-mm-dd")
    private LocalDate checkOutDate;

    @Schema(name = "Product price", example = "$100.00", required = true)
    private double bookingPrice;

    @Schema(name = "CONFIRMED,CANCELED" , example = "CONFIRMED" , required = true)
    private Booking.BookingStatus status;

    private String description;

   // private Apartment apartment;

}
