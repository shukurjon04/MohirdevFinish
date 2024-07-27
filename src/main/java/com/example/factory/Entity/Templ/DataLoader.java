package com.example.factory.Entity.Templ;

import com.example.factory.Entity.EMPLOYEE.Role;
import com.example.factory.Enums.RoleName;
import com.example.factory.Enums.RoleNamePermission;
import com.example.factory.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.example.factory.Enums.RoleNamePermission.*;


@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String InitMode;

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void run(String... args) throws Exception {

        if (InitMode.equals("always")){

            Role Director = new Role();
            Director.setName(RoleName.Director.getName());
            Director.setPermissions(Set.of(RoleNamePermission.values()));
            roleRepository.save(Director);

            Role Staff = new Role();
            Staff.setName(RoleName.Staff.getName());
            Staff.setPermissions(Set.of(VIEW_SELF_CUSTOMER,EDIT_CUSTOMER,ARCHIVE_CUSTOMER,GET_CUSTOMER_INFORMATION,REGISTER_CUSTOMER));
            roleRepository.save(Staff);

            Role Foreman = new Role();
            Foreman.setName(RoleName.Foreman.getName());
            Foreman.setPermissions(Set.of(RoleNamePermission.values()));
            roleRepository.save(Foreman);

        }
    }
}
