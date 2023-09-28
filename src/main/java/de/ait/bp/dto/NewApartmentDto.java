package de.ait.bp.dto;

import de.ait.bp.models.Apartment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewApartmentDto {
    @NotNull
    @NotBlank
    @NotEmpty
    private String type;

    private String description;

    @NotNull
    @NotBlank
   // @Pattern(regexp ="\"^[a-zA-Z0-9\\\\s\\\\.,-]+,\\\\s*\\\\d+,\\\\s*\\\\d{5},\\\\s*[a-zA-Z\\\\s]+,\\\\s*[a-zA-Z\\\\s]+,\\\\s*[a-zA-Z\\\\s]+$\""  ,message = "Address must be in the format ")
    @Schema(example = "Street,House number,Index ,City")
    private String address;

    @NotNull
    private int numberOfRooms;

    @Schema(name = "CONFIRMED,DELETED" , example = "CONFIRMED" , required = true)
    private Apartment.State state;

}
