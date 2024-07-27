package com.example.factory.Auditing;

import com.example.factory.Repository.CustomerRepository;
import com.example.factory.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditingConfig {

    @Bean
    public AuditingSecurity auditorAware(){
        return new AuditingSecurity();
    }
}
