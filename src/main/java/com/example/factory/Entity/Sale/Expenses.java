package com.example.factory.Entity.Sale;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String Description;
    @Column(nullable = false)
    private String TypeOfAdvertisement;
    @Column(nullable = false)
    private Double CostOfAdvertisement;
    @Column(nullable = false)
    private Date ExpirationDate;
    @Column(nullable = false)
    private Date IssuedDate;
    @Column(nullable = false,updatable = false)
    @CreatedBy
    private String CreatedBy;

}
