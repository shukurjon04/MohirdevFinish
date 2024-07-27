package com.example.factory.Controller.Work_WIth_Customer;

import com.example.factory.DTO.CustomerDtoss.CustomerDto;
import com.example.factory.DTO.LoginDto;
import com.example.factory.DTO.RegisterDTO;
import com.example.factory.DTO.Responses.ApiResponse;
import com.example.factory.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class Customer_Controller {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<?> passport(@Valid @RequestBody CustomerDto registerDTO){
        ApiResponse response = customerService.RegisterCustomer(registerDTO);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping("/Get_Information")
    public ResponseEntity<?> getInformation(){
        ApiResponse response = customerService.getAll();
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PutMapping("/edit_customer")
    public ResponseEntity<?> edit(@RequestParam UUID id , @RequestBody CustomerDto registerDTO ){
        ApiResponse response = customerService.EditCustomer(id, registerDTO);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @DeleteMapping("/delete_customer")
    public ResponseEntity<?> delete(@RequestParam UUID id ){
        ApiResponse response = customerService.delete(id);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
