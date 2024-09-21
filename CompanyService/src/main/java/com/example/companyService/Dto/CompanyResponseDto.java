package com.example.companyService.Dto;

import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompanyResponseDto {
    private String name;
    private String description;
    @ElementCollection
    private List<String> pedidos;
}
