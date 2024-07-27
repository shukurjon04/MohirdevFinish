package com.example.factory.Auditing;

import com.example.factory.Entity.EMPLOYEE.Employee;
import com.example.factory.Repository.CustomerRepository;
import com.example.factory.Repository.EmployeeRepository;
import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

public class AuditingSecurity implements AuditorAware<String> {


    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails && !authentication.getPrincipal().equals("anonymousUser")) {
             return Optional.of(((UserDetails) authentication.getPrincipal()).getUsername());
        }
        return Optional.empty();
    }
}
