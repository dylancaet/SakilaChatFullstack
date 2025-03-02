package com.rental.sakila.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Table(name = "store")
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class Store
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Byte id;
}
