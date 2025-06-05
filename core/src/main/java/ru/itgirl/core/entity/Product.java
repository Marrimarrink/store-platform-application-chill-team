package ru.itgirl.core.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_product", length = 50, nullable = false)
    private String name_product;
    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id")
    private Company company;
}