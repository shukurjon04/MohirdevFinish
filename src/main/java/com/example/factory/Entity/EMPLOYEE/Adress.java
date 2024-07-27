package com.example.factory.Entity.EMPLOYEE;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String Region;

    private String District;

    private String Village;

    private String Home;
}
