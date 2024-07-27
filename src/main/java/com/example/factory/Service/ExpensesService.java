package com.example.factory.Service;

import com.example.factory.DTO.Responses.ApiResponse;
import com.example.factory.DTO.SaleDto.ExpensesDto;
import com.example.factory.Entity.Sale.Expenses;
import com.example.factory.Repository.ExpensesRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ExpensesService {

    @Autowired
    private ExpensesRepository expensesRepository;

    public ApiResponse page(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Expenses> all = expensesRepository.findAll(pageable);
        return new ApiResponse("ok",true,200,all);

    }

    public ApiResponse Add(ExpensesDto expenses) {
        if (expensesRepository.existsByCostOfAdvertisementAndDescriptionAndTypeOfAdvertisement(expenses.getCostOfAdvertisement(),
                expenses.getDescription(), expenses.getTypeOfAdvertisement())){
            return new ApiResponse("fail",false,400,null);
        }
        Expenses newExpenses = new Expenses();
        newExpenses.setCostOfAdvertisement(expenses.getCostOfAdvertisement());
        newExpenses.setDescription(expenses.getDescription());
        newExpenses.setTypeOfAdvertisement(expenses.getTypeOfAdvertisement());
        newExpenses.setIssuedDate(expenses.getIssuedDate());
        newExpenses.setExpirationDate(expenses.getExpirationDate());
        Expenses save = expensesRepository.save(newExpenses);
        return new ApiResponse("ok",true,200,save);
    }

    public ApiResponse edit(UUID id , ExpensesDto expenses) {
        Optional<Expenses> expenses1 = expensesRepository.findById(id);
        if (expenses1.isPresent()) {
            Expenses expenses2 = expenses1.get();
            expenses2.setDescription(expenses.getDescription());
            expenses2.setIssuedDate(expenses.getIssuedDate());
            expenses2.setExpirationDate(expenses.getExpirationDate());
            expenses2.setCostOfAdvertisement(expenses.getCostOfAdvertisement());
            expenses2.setTypeOfAdvertisement(expenses.getTypeOfAdvertisement());
            Expenses save = expensesRepository.save(expenses2);
            return new ApiResponse("UPDATED",true,200,save);
        }
        return new ApiResponse("fail",false,400,null);
    }

    public ApiResponse delete(UUID id) {
        try {
            expensesRepository.deleteById(id);
            return new ApiResponse("deleted",true,200,null);
        }catch (Exception e){
            return new ApiResponse("fail",false,400,e);
        }
    }
}
