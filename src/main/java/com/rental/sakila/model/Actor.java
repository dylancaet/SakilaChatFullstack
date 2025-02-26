package com.rental.sakila.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.util.List;

@Entity
@Table(name = "actor")
@Getter
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="actor_id")
    private Short id;

    @Setter
    @Column(name="first_name")
    private String firstName;

    @Setter
    @Column(name="last_name")
    private String lastName;

    @Formula("concat(first_name, ' ', last_name)")
    private String fullName;

    public String getFullName() {
        return firstName + ' ' + lastName;
    }
}
