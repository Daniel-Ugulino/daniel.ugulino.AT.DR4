package com.example.itemService.Dto;

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
}
