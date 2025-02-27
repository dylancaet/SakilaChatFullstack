package com.rental.sakila.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Table(name = "store")
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="store_id")
    private Byte id;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
}