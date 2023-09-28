package de.ait.bp.dto;

import de.ait.bp.models.Apartment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApartmentDto {

    private Long apartmentId;

    private String type;

    private String description;

    private String address;

    private int numberOfRooms;

    private Apartment.State state;

    public static ApartmentDto from(Apartment apartment) {
        ApartmentDto result = builder()
                .apartmentId(apartment.getId())
                .type(apartment.getType())
                .description(apartment.getDescription())
                .address(apartment.getAddress())
                .numberOfRooms(apartment.getNumberOfRooms())
                .state(apartment.getState())
                .build();
        return result;
    }



}
