package com.example.rentService.Dto;

import com.example.rentService.Domain.Enum.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemDto {
    private String name;
    private String description;
    private Integer amount;
    private Double price;
}
