package com.example.companyService.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
}
