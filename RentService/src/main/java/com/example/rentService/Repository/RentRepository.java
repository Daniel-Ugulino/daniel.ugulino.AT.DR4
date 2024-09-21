package com.example.rentService.Repository;

import com.example.rentService.Domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<Rent, Long> {
}
