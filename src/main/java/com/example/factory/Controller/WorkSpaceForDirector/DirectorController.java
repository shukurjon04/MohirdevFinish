package com.example.factory.Controller.WorkSpaceForDirector;

import com.example.factory.Entity.EMPLOYEE.Employee;
import com.example.factory.Repository.EmployeeRepository;
import com.example.factory.Service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Director")
public class DirectorController {
    @Autowired
    private DirectorService directorService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("get")
    public ResponseEntity<?> getDirector() {
        List<Object[]> percentage = employeeRepository.findPercentage();
        List<Object[]> employeeCount = employeeRepository.findEmployeeCount();
        List<Object> objects = new ArrayList<>();
        objects.add(percentage);
        objects.add(employeeCount);
        return ResponseEntity.ok(objects);
    }
    @PostMapping("/filter")
    public ResponseEntity<?> filterDirector(@RequestParam int age1, @RequestParam int age2) {
        List<Object[]> percentage = employeeRepository.findByAge(age1, age2);
        return ResponseEntity.ok(percentage);
    }
    @GetMapping("/getpage")
    public ResponseEntity<?> getDirectorPage(@RequestParam int page , @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> all = employeeRepository.findAll(pageable);
        return ResponseEntity.ok(all);
    }
    @GetMapping("/salary")
    public ResponseEntity<?> getDirectorSalary() {
        Long l= employeeRepository.findSumSalary();
        return ResponseEntity.ok(l);
    }
}
