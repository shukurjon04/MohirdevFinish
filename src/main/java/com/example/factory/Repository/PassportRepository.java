package com.example.factory.Repository;


import com.example.factory.Entity.EMPLOYEE.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface PassportRepository extends JpaRepository<Passport, UUID> {

    @Query("select (count(p) > 0) from Passport p where p.JSHSHIR = ?1")
    boolean existsByJSHSHIR(String JSHSHIR);
}