package com.rental.sakila.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.testng.annotations.Optional;

@Setter
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

    @Nullable
    @Column(name = "language_id")
    private Byte languageId;
}
