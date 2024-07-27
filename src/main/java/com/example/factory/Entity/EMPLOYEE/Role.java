package com.example.factory.Entity.EMPLOYEE;


import com.example.factory.Enums.RoleNamePermission;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch=FetchType.LAZY)
    private Set<RoleNamePermission> permissions;
}
