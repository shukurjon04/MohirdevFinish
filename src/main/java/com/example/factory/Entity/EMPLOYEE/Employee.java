package com.example.factory.Entity.EMPLOYEE;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Employee implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String FirstName;
    @Column(nullable = false)
    private String LastName;
    @Column(nullable = false)
    private String Sharif;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String username;

    private double Salary;
    @OneToOne(fetch = FetchType.EAGER)
    private Passport passport;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Adress> adress;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> role;
    @Column(nullable = false,unique = true)
    private String department;
    @Column(nullable = false)
    private int Age;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.stream().map(role1 -> new SimpleGrantedAuthority(role1.getName()))
                .collect(Collectors.toSet());
    }
}
