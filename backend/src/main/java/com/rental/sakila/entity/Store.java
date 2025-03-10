package com.rental.sakila.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "store")
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Store
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    @Positive
    private Byte id;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
}
