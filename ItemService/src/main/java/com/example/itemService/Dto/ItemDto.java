package com.example.itemService.Dto;

import com.example.itemService.Domain.Enum.ItemType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemDto {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private ItemType type;
    @NotNull
    private Integer amount;
    @NotNull
    private Double price;
}
