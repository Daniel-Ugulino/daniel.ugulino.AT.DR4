package com.example.rentService.Controller;

import com.example.rentService.Domain.Enum.Status;
import com.example.rentService.Domain.Rent;

import com.example.rentService.Dto.RentDto;
import com.example.rentService.Dto.StatusDto;
import com.example.rentService.Service.RentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent")
public class RentController {
    @Autowired
    RentService rentService;

    @PostMapping()
    public ResponseEntity<Object> add(@RequestBody @Valid RentDto rentDto) {
        try {
            Rent rentEntity = rentService.save(rentDto);
            return ResponseEntity.status(HttpStatus.OK).body(rentEntity);
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
            Rent rentEntity = rentService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(rentEntity);
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
            List<Rent> rents = rentService.list();
            return ResponseEntity.status(HttpStatus.OK).body(rents);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data integrity violation");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody @Valid RentDto rentDto, @PathVariable Long id) {
        try {
            Rent rentEntity = rentService.update(rentDto,id);
            return ResponseEntity.status(HttpStatus.OK).body(rentEntity);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data integrity violation");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody StatusDto status, @PathVariable Long id) {
        try {
            Rent rentEntity = rentService.changeStatus(status.getStatus(),id);
            return ResponseEntity.status(HttpStatus.OK).body(rentEntity);
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
            rentService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Item Deleted");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data integrity violation");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
