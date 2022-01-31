package com.company.store.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleDTO {
    @NotBlank(message = "Необходимо указать наименование роли")
    private String title;
}
