package com.example.factory.Entity.EMPLOYEE;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, length = 2)
    private String Series;
    @Column(nullable = false, length = 7,unique = true)
    private long Number;
    @Column(nullable = false,unique = true)
    private String JSHSHIR;
    @Column(nullable = false)
    private String Nationality;


}
