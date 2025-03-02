package com.rental.sakila.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Table(name = "store")
@Entity
public class Store
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Byte id;
}
