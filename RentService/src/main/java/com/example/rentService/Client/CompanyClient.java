package com.example.rentService.Client;

import com.example.rentService.Dto.CompanyDto;
import com.example.rentService.Dto.ItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "company-service", url = "http://localhost:8082/company/")
public interface CompanyClient {
    @GetMapping("{id}")
    CompanyDto getItemById(@PathVariable("id") Long id);
}
