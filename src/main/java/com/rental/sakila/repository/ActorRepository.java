package com.rental.sakila.repository;

import com.rental.sakila.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Short> {
    public List<Actor> findByFullNameContainingIgnoreCase(String name);
}
