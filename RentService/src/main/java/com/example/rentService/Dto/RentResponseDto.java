package com.example.rentService.Dto;

import com.example.rentService.Domain.Enum.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RentResponseDto {
    private Long id;
    private Long companyId;
    private Long itemId;
    @Enumerated(EnumType.STRING)
    private Status type;
    private Integer amount;
    private Double totalPrice;
}
