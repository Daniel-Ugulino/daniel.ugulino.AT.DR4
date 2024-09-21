package com.example.companyService.Domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "orders", joinColumns = @JoinColumn(name = "order_id"))
    private List<Long> orders = new ArrayList<>();

    public void addOrder(Long order) {
        if (this.orders == null) {
            this.orders = new ArrayList<>(); // Ensure list is initialized
        }
        this.orders.add(order);
    }
}
