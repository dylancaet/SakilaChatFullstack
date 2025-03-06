package com.rental.sakila.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "film")
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Film
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    @Positive
    private Short id;

    @Column(name = "title")
    private String title;

    @Column(name = "replacement_cost")
    private float price;

    @Column(name = "description")
    private String description;
}
