package com.example.factory.Repository;


import com.example.factory.Entity.EMPLOYEE.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    @Query("select e from Employee e where e.username = ?1")
    Optional<Employee> findByUsername(String username);

    @Query("select (count(e) > 0) from Employee e where e.username = ?1")
    boolean existsByUsername(String username);
    @Query("select count (e.id) from Employee e group by e.department")
    List<Object[]> findEmployeeCount();
    @Query("select e from Employee e where e.Age = ?1")
    List<Employee> findByAge(int age);
    @Query("select e from Employee e where e.Age between ?1 and ?2")
    List<Object[]> findByAge(int age1, int age2);
    @Query("select sum (e.Salary) from Employee e")
    Long findSumSalary();
    @Query("select e.department,count (e) as title , count (e) *100.0 /(count (*)) as percent from Employee e GROUP BY e.department")
    List<Object[]> findPercentage();

}
