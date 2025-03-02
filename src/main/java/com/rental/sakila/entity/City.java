package com.rental.sakila.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Table(name="city")
@Entity
@NoArgsConstructor
@EqualsAndHashCode
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

    public City(Short id, String cityName)
    {
        this.id = id;
        this.cityName = cityName;
    }
}
