package com.example.factory.Repository;


import com.example.factory.Entity.EMPLOYEE.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query("select r from Role r where r.name = ?1")
    Optional<Role> findByName(String name);
}
