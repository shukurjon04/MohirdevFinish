package com.example.factory.Service;

import com.example.factory.DTO.AdresDTO;
import com.example.factory.DTO.RegisterDTO;
import com.example.factory.DTO.Responses.ApiResponse;
import com.example.factory.Entity.EMPLOYEE.Adress;
import com.example.factory.Entity.EMPLOYEE.Employee;
import com.example.factory.Entity.EMPLOYEE.Passport;
import com.example.factory.Entity.EMPLOYEE.Role;
import com.example.factory.JWTToken.JwtProvider;
import com.example.factory.Repository.AdressRepository;
import com.example.factory.Repository.EmployeeRepository;
import com.example.factory.Repository.PassportRepository;
import com.example.factory.Repository.RoleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final PassportRepository passportRepository;
    private final JwtProvider jwtProvider;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationProvider authenticationProvider;
    private final AdressRepository adressRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, PassportRepository passportRepository, @Lazy JwtProvider jwtProvider, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationProvider authenticationProvider,
                           AdressRepository adressRepository) {
        this.employeeRepository = employeeRepository;
        this.passportRepository = passportRepository;
        this.jwtProvider = jwtProvider;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationProvider = authenticationProvider;
        this.adressRepository = adressRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("not found"));
        return new User(employee.getUsername(),
                employee.getPassword(),
                employee.isEnabled(),
                employee.isAccountNonExpired(),
                employee.isCredentialsNonExpired(),
                employee.isAccountNonLocked(),
                employee.getAuthorities());
    }

    public ApiResponse RegisterEmployee(@Valid RegisterDTO registerDTO) {
        if (passportRepository.existsByJSHSHIR(registerDTO.getJSHSHIR())) {
            return new ApiResponse("this passport is already use", false, 400, null);
        }
        Passport passport = new Passport();
        passport.setSeries(registerDTO.getSeries());
        passport.setNumber(registerDTO.getNumber());
        passport.setJSHSHIR(registerDTO.getJSHSHIR());
        passport.setNationality(registerDTO.getNationality());
        Passport save = passportRepository.save(passport);
        if (employeeRepository.existsByUsername(registerDTO.getUsername())) {
            return new ApiResponse("this username is already use", false, 400, null);
        }
        Employee employee = new Employee();
        employee.setDepartment(registerDTO.getDepartment());
        employee.setAge(registerDTO.getAge());
        employee.setUsername(registerDTO.getUsername());
        employee.setFirstName(registerDTO.getFirstName());
        employee.setLastName(registerDTO.getLastName());
        employee.setSharif(registerDTO.getSharif());
        employee.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        employee.setSalary(54.326801);
        Role role = roleRepository.findByName(registerDTO.getRole()).orElseThrow(() -> new UsernameNotFoundException("role not found"));

        employee.setRole(Set.of(role));
        employee.setPassport(save);

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
        employee.setAdress(adresses);
        Employee save1 = employeeRepository.save(employee);
        return new ApiResponse("seccess", true, 200, save1);

    }

    @Autowired
    private AuthenticationManager  authenticationManager;
    public ApiResponse Login(String userName, String password) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        if (authenticate.isAuthenticated()) {
            UserDetails userDetails = loadUserByUsername(userName);
            return new ApiResponse("success", true, 200, jwtProvider.generateToken(userDetails.getUsername()));
        }
        return new ApiResponse("Not found ", false, 400, null);

    }

    public ApiResponse getAll() {
        List<Employee> all = employeeRepository.findAll();
        return new ApiResponse("success", true, 200, all);
    }

    public ApiResponse EditEmploye(UUID id, RegisterDTO registerDTO) {
        if (!employeeRepository.existsByUsername(registerDTO.getUsername())) {
            return new ApiResponse("this employee is not exist", false, 400, null);
        }
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("employee not found"));
        Passport passport = employee.getPassport();
        passport.setSeries(registerDTO.getSeries());
        passport.setNumber(registerDTO.getNumber());
        passport.setJSHSHIR(registerDTO.getJSHSHIR());
        passport.setNationality(registerDTO.getNationality());
        Passport save = passportRepository.save(passport);

        employee.setFirstName(registerDTO.getFirstName());
        employee.setLastName(registerDTO.getLastName());
        employee.setSharif(registerDTO.getSharif());
        employee.setSalary(registerDTO.getSalary());
        employee.setPassport(save);
        Employee save1 = employeeRepository.save(employee);
        List<Adress> adress = new ArrayList<>();
        for (Adress adress1 : save1.getAdress()) {
            adress1.setHome(adress1.getHome());
            adress1.setRegion(adress1.getRegion());
            adress1.setDistrict(adress1.getDistrict());
            adress1.setVillage(adress1.getVillage());
            Adress save2 = adressRepository.save(adress1);
            adress.add(save2);
        }
        employee.setAdress(adress);
        Employee save2 = employeeRepository.save(employee);
        return new ApiResponse("success", true, 200, save2);


    }

    public ApiResponse delete(UUID id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("employee not found"));
        employeeRepository.deleteAllById(Collections.singleton(id));
        passportRepository.delete(employee.getPassport());
        adressRepository.deleteAll(employee.getAdress());
        roleRepository.deleteAll(employee.getRole());
        return new ApiResponse("deleted", true, 200, null);

    }
}
