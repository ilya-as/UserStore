package com.company.store.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Schema(description = "Пользователь и список его ролей")
public class UserDTO {
    @NotBlank(message = "Необходимо указать имя пользователя")
    private String name;
    @Schema(description = "Список ролей пользователя")
    private Set<RoleDTO> roles = new HashSet<>();
}
