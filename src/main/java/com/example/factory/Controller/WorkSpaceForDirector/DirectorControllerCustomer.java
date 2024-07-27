package com.example.factory.Controller.WorkSpaceForDirector;

import com.example.factory.Entity.Customer.Customers;
import com.example.factory.Entity.EMPLOYEE.Employee;
import com.example.factory.Repository.CustomerRepository;
import com.example.factory.Repository.EmployeeRepository;
import com.example.factory.Service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Director/Customer")
public class DirectorControllerCustomer {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/get")
    public ResponseEntity<?> getDirector(@RequestParam Timestamp timestamp) {
        List<Customers> customers = customerRepository.findByCreatedAt(timestamp);
        return ResponseEntity.ok(customers);
    }
    @PostMapping("/filter")
    public ResponseEntity<?> filterDirector() {
        List<Object[]> topEmployeeByCustomerRegistrations = customerRepository.findTopEmployeeByCustomerRegistrations();
        return ResponseEntity.ok(topEmployeeByCustomerRegistrations);
    }
    @GetMapping("/getpage")
    public ResponseEntity<?> getDirectorPage(@RequestParam int page , @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Object[]> top3EmployeesByCustomerRegistrations = customerRepository.findTop3EmployeesByCustomerRegistrations(pageable);
        return ResponseEntity.ok(top3EmployeesByCustomerRegistrations);
    }
    @GetMapping("/salary")
    public ResponseEntity<?> getDirectorSalary(Date start, Date end) {
        long l = customerRepository.countCustomersRegisteredInLastMonth(start, end);
        return ResponseEntity.ok(l);
    }
    @GetMapping("/month")
    public ResponseEntity<?> getDirectormonth(Date start, Date end) {
        List<Object[]> l = customerRepository.findMostRegistrationsInLastMonth(start,end);
        return ResponseEntity.ok(l);
    }
}
