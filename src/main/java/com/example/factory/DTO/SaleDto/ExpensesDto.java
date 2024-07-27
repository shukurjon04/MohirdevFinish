package com.example.factory.DTO.SaleDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExpensesDto {
    @NotBlank
    public String description;
    @NotBlank
    public String TypeOfAdvertisement;
    @NotNull
    public Double CostOfAdvertisement;
    @NotNull
    public Date ExpirationDate;
    @NotNull
    public Date IssuedDate;
}
