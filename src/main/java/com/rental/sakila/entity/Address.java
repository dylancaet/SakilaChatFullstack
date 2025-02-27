package com.rental.sakila.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Table(name="address")
@Entity
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private Short id;

    @Column(name="address")
    private String address;

    @Column(name="district")
    private String districtName;

    @Column(name="postal_code")
    @Size(max=10)
    @Nullable
    private String postalCode;

    @Column(name="phone")
    @Size(max=20)
    @Nullable
    private String contactNumber;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
}
