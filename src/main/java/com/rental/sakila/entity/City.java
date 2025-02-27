package com.rental.sakila.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Table(name="city")
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="city_id")
    private Short id;

    @Column(name="city")
    private String cityName;

    @JsonIgnore
    @OneToMany(mappedBy = "city")
    private List<Address> addresses;
}
