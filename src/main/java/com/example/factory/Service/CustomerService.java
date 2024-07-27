package com.example.factory.Service;

import com.example.factory.DTO.AdresDTO;
import com.example.factory.DTO.CustomerDtoss.CustomerDto;
import com.example.factory.DTO.RegisterDTO;
import com.example.factory.DTO.Responses.ApiResponse;
import com.example.factory.Entity.Customer.Customers;
import com.example.factory.Entity.EMPLOYEE.Adress;
import com.example.factory.Entity.EMPLOYEE.Employee;
import com.example.factory.Entity.EMPLOYEE.Passport;
import com.example.factory.Entity.EMPLOYEE.Role;
import com.example.factory.Repository.AdressRepository;
import com.example.factory.Repository.CustomerRepository;
import com.example.factory.Repository.EmployeeRepository;
import com.example.factory.Repository.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomerService {

    @Autowired
    private PassportRepository passportRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AdressRepository adressRepository;

    

    public ApiResponse RegisterCustomer(CustomerDto registerDTO) {
        if (passportRepository.existsByJSHSHIR(registerDTO.getJSHSHIR())) {
            return new ApiResponse("this passport is already use", false, 400, null);
        }
        Passport passport = new Passport();
        passport.setSeries(registerDTO.getSeries());
        passport.setNumber(registerDTO.getNumber());
        passport.setJSHSHIR(registerDTO.getJSHSHIR());
        passport.setNationality(registerDTO.getNationality());
        Passport save = passportRepository.save(passport);

        Customers customers = new Customers();
        customers.setFirstName(registerDTO.getFirstName());
        customers.setLastName(registerDTO.getLastName());
        customers.setPassport(save);

        List<Adress> adresses = new ArrayList<>();
        for (AdresDTO adresDTO : registerDTO.getAdresDTO()) {
            if (adressRepository.existsByHome(adresDTO.getHome())) {
                return new ApiResponse("this " + adresDTO.getHome() + " is already use", false, 400, null);
            }
            Adress adress = new Adress();
            adress.setRegion(adresDTO.getRegion());
            adress.setDistrict(adresDTO.getDistrict());
            adress.setVillage(adresDTO.getVillage());
            adress.setHome(adresDTO.getHome());
            Adress save1 = adressRepository.save(adress);
            adresses.add(save1);
        }
        customers.setAddresses(adresses);
        Customers save1 = customerRepository.save(customers);
        return new ApiResponse("seccess", true, 200, save1);
    }
    public ApiResponse delete(UUID id) {
        Customers customers = customerRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("customer not found"));
        customerRepository.deleteAllById(Collections.singleton(id));
        passportRepository.delete(customers.getPassport());
        adressRepository.deleteAll(customers.getAddresses());
        return new ApiResponse("deleted", true, 200, null);
    }
    public ApiResponse EditCustomer(UUID id, CustomerDto customerDto) {
        if (!customerRepository.existsById(id)) {
            return new ApiResponse("this employee is not exist", false, 400, null);
        }
        Customers customers = customerRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("employee not found"));
        Passport passport = customers.getPassport();
        passport.setSeries(customerDto.getSeries());
        passport.setNumber(customerDto.getNumber());
        passport.setJSHSHIR(customerDto.getJSHSHIR());
        passport.setNationality(customerDto.getNationality());
        Passport save = passportRepository.save(passport);

        customers.setFirstName(customerDto.getFirstName());
        customers.setLastName(customerDto.getLastName());
        customers.setPassport(save);
        Customers save1 = customerRepository.save(customers);
        List<Adress> adress = new ArrayList<>();
        for (Adress adress1 : save1.getAddresses()) {
            adress1.setHome(adress1.getHome());
            adress1.setRegion(adress1.getRegion());
            adress1.setDistrict(adress1.getDistrict());
            adress1.setVillage(adress1.getVillage());
            Adress save2 = adressRepository.save(adress1);
            adress.add(save2);
        }
        customers.setAddresses(adress);
        Customers save2 = customerRepository.save(customers);
        return new ApiResponse("success", true, 200, save2);


    }
    public ApiResponse getAll() {
        List<Customers> all = customerRepository.findAll();
        return new ApiResponse("customers", true, 200, all);
    }



}
