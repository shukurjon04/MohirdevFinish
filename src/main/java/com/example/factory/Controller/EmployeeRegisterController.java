package com.example.factory.Controller;


import com.example.factory.DTO.LoginDto;
import com.example.factory.DTO.RegisterDTO;
import com.example.factory.DTO.Responses.ApiResponse;
import com.example.factory.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("auth/employee")
public class EmployeeRegisterController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<?> passport(@Valid @RequestBody RegisterDTO registerDTO){
        System.out.println(registerDTO);
        ApiResponse response = employeeService.RegisterEmployee(registerDTO);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PostMapping("/register/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto){
        ApiResponse response = employeeService.Login(loginDto.getUsername(),loginDto.getPassword());
`        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping("/Get_Information")
    public ResponseEntity<?> getInformation(){
        ApiResponse response = employeeService.getAll();
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PutMapping("/edit_Employee")
    public ResponseEntity<?> edit(@RequestParam UUID id , @RequestBody RegisterDTO registerDTO ){
        ApiResponse response = employeeService.EditEmploye(id, registerDTO);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @DeleteMapping("/delete_employee")
    public ResponseEntity<?> delete(@RequestParam UUID id ){
        ApiResponse response = employeeService.delete(id);
        return ResponseEntity.status(response.getCode()).body(response);
    }




}
