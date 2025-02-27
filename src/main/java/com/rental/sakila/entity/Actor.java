package com.rental.sakila.entity;

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

    @Setter(AccessLevel.PUBLIC)
    @Column(name="first_name")
    private String firstName;

    @Setter(AccessLevel.PUBLIC)
    @Column(name="last_name")
    private String lastName;

    @Formula("concat(first_name, ' ', last_name)")
    private String fullName;

    @ManyToMany
    @JoinTable(name = "film_actor", joinColumns = @JoinColumn(name = "actor_id"), inverseJoinColumns = @JoinColumn(name = "film_id"))
    private List<Film> films;

    public String getFullName() {
        return firstName + ' ' + lastName;
    }
}
