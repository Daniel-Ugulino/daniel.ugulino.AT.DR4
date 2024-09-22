package com.example.companyService.Dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RentDto {
    private Long id;
    private Long companyId;
    private Long itemId;
    private Integer amount;
    private Boolean restore;
}
