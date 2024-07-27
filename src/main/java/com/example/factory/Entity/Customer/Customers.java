package com.example.factory.Entity.Customer;

import com.example.factory.Entity.EMPLOYEE.Adress;
import com.example.factory.Entity.EMPLOYEE.Employee;
import com.example.factory.Entity.EMPLOYEE.Passport;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String FirstName;
    @Column(nullable = false)
    private String LastName;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Adress> Addresses;
    @OneToOne(fetch = FetchType.EAGER)
    private Passport passport;
    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    private Timestamp CreatedAt;
    @Column(updatable = false)
    @CreatedBy
    private String createdBy;
}
