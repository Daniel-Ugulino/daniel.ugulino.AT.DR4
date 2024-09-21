package com.example.rentService.Dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RentDto {
    private Long companyId;
    private Long itemId;
    private Integer amount;
    private Double totalPrice;
}
