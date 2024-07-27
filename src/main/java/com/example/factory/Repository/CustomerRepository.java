package com.example.factory.Repository;

import com.example.factory.Entity.Customer.Customers;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, UUID> {

    List<Customers> findByCreatedAt(Timestamp createdAt);

    @Query("SELECT c.createdBy, COUNT(c) as totalCustomers FROM Customers c GROUP BY c.createdBy ORDER BY totalCustomers DESC")
    List<Object[]> findTopEmployeeByCustomerRegistrations();

    @Query("SELECT c.createdBy, COUNT(c) as totalCustomers FROM Customers c GROUP BY c.createdBy ORDER BY totalCustomers DESC")
    List<Object[]> findTop3EmployeesByCustomerRegistrations(Pageable pageable);

    @Query("SELECT COUNT(c) FROM Customers c WHERE c.CreatedAt >= :startDate AND c.CreatedAt <= :endDate")
    long countCustomersRegisteredInLastMonth(Date startDate, Date endDate);

    @Query("SELECT c.createdBy, COUNT(c) as totalCustomers FROM Customers c WHERE c.createdBy >= :startDate AND c.createdBy <= :endDate GROUP BY c.createdBy ORDER BY totalCustomers DESC")
    List<Object[]> findMostRegistrationsInLastMonth(Date startDate, Date endDate);

}
