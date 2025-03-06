package com.rental.sakila.repository;

import com.rental.sakila.entity.Film;
import com.rental.sakila.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FilmRepository extends JpaRepository<Film, Short>, PagingAndSortingRepository<Film, Short>
{
    public Page<Film> findByTitleContainingIgnoreCase(String name, Pageable pageable);
}

