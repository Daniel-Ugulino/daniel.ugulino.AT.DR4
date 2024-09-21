package com.example.itemService.Controller;

import com.example.itemService.Domain.Item;

import com.example.itemService.Dto.ItemDto;
import com.example.itemService.Service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;

    @PostMapping()
    public ResponseEntity<Object> add(@RequestBody @Valid ItemDto itemDto) {
        try {
            Item itemEntity = itemService.save(itemDto);
            return ResponseEntity.status(HttpStatus.OK).body(itemEntity);
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
            List<Item> items = itemService.list();
            return ResponseEntity.status(HttpStatus.OK).body(items);
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
            Item itemEntity = itemService.getById(id);
            return ResponseEntity.status(HttpStatus.OK).body(itemEntity);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data integrity violation");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody @Valid ItemDto itemDto, @PathVariable Long id) {
        try {
            Item itemEntity = itemService.update(itemDto,id);
            return ResponseEntity.status(HttpStatus.OK).body(itemEntity);
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
            itemService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Item Deleted");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data integrity violation");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
