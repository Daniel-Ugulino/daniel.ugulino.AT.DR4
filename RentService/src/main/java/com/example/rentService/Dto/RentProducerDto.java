package com.example.rentService.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RentProducerDto {
    private Long id;
    private Long companyId;
    private Long itemId;
    private Integer amount;
}
