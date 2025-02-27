package com.rental.sakila.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Year;

/*
    name
    release year
    original language
    rental rate
    replacement cost


    total inventory sold
    total customers sold
    stores { [
        country,
        city,
        inventory_received,
        inventory_sold
    ] }
 */

@Entity
@Table(name = "film")
@Getter
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="film_id")
    private Short id;

    @Column(name="title")
    private String title;

    @Column(name="release_year")
    private Year releaseYear;

    @Column(name="rental_rate")
    private float rentalRate;

    @Column(name="price")
    private float price;
}
