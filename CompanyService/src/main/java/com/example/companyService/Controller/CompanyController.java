package com.example.companyService.Controller;

import com.example.companyService.Domain.Company;

import com.example.companyService.Dto.CompanyDto;
import com.example.companyService.Service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @PostMapping()
    public ResponseEntity<Object> add(@RequestBody @Valid CompanyDto companyDto) {
        try {
            Company companyEntity = companyService.save(companyDto);
            return ResponseEntity.status(HttpStatus.OK).body(companyEntity);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data integrity violation");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        try {
            Company companyEntity = companyService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(companyEntity);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data integrity violation");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @GetMapping()
    public ResponseEntity<Object> list() {
        try {
            List<Company> companies = companyService.list();
            return ResponseEntity.status(HttpStatus.OK).body(companies);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data integrity violation");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody @Valid CompanyDto companyDto, @PathVariable Long id) {
        try {
            Company companyEntity = companyService.update(companyDto,id);
            return ResponseEntity.status(HttpStatus.OK).body(companyEntity);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data integrity violation");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id) {
        try {
            companyService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Company Deleted");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data integrity violation");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
