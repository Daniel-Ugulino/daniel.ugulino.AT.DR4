package com.example.rentService.Client;

import com.example.rentService.Dto.ItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "item-service", url = "http://localhost:8083/item/")
public interface ItemClient {
    @GetMapping("{id}")
    ItemDto getItemById(@PathVariable("id") Long id);
}
