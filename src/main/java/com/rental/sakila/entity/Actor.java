package com.rental.sakila.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.util.List;

@Entity
@Table(name = "actor")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="actor_id")
    @NonNull
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

    public boolean isValid()
    {
        if (firstName.length() <= 0)
            return false;

        if (lastName.length() <= 0)
            return false;

        if (!fullName.equals(getFullName()))
            return false;

        return true;
    }
}
