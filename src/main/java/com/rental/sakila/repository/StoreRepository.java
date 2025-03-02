package com.rental.sakila.repository;

import com.rental.sakila.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Byte>
{
}
