package com.example.factory.Controller.Work_WIth_Sale;

import com.example.factory.DTO.Responses.ApiResponse;
import com.example.factory.DTO.SaleDto.ExpensesDto;
import com.example.factory.Service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/expenses")
public class ExpensesController {

    @Autowired
    private ExpensesService expensesService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestParam int page, @RequestParam int size){
        ApiResponse response = expensesService.page(page,size);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PostMapping("/Add_Inform")
    public ResponseEntity<?> Add_Inform(@RequestBody ExpensesDto expenses){
        ApiResponse response = expensesService.Add(expenses);
        return ResponseEntity.status(response.getCode()).body(response);
    }
    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestParam UUID id, @RequestBody ExpensesDto expenses){
        ApiResponse response = expensesService.edit(id, expenses);
        return ResponseEntity.status(response.getCode()).body(response);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam UUID id){
        ApiResponse response = expensesService.delete(id);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
