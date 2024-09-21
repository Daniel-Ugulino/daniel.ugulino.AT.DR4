package com.example.itemService.Dto;

import com.example.itemService.Domain.Enum.ItemType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemResponseDto {
    private Long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private ItemType type;
    private Integer amount;
    private Double price;
}
