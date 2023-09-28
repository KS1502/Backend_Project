package de.ait.bp.dto;

import de.ait.bp.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "System User")
public class UserDto {

    @Schema(description = "User Identifier", example = "1")
    private Long id;

    @Schema(description = "User Email", example = "simple@mail.com" )
    private String email;

    @Schema(description = "User Role: MANAGER, GUEST, ADMIN", example = "GUEST")
    private String role;

    @Schema(description = "User Status: NOT_CONFIRMED, CONFIRMED , BANNED ", example = "CONFIRMED")
    private String state;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .state(user.getState().name())
                .role(user.getRole().name())
                .build();
    }

    public static List<UserDto> from(Collection<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}
