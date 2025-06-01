package ru.itgirl.core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "name_company",
            inverseJoinColumns = @JoinColumn(name = "company_id", referencedColumnName = "id"),
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private Set<Company> companies;
}