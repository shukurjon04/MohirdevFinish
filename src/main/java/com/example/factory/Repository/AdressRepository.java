package com.example.factory.Repository;

import com.example.factory.Entity.EMPLOYEE.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AdressRepository extends JpaRepository<Adress, UUID> {

    @Query("select (count(a) > 0) from Adress a where a.Home = ?1")
    boolean existsByHome(String home);
}