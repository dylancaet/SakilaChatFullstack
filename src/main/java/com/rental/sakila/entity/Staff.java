package com.rental.sakila.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "store")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Staff
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    @Positive
    public Byte id;

    @Column(name="first_name")
    public String firstName;

    @Column(name="last_name")
    public String lastName;
}
