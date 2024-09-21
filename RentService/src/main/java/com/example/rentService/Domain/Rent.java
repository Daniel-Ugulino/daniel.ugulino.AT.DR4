package com.example.rentService.Domain;
import com.example.rentService.Domain.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rent")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long companyId;
    private Long itemId;
    @Enumerated(EnumType.STRING)
    private Status type;
    private Integer amount;
    private Double totalPrice;
}
