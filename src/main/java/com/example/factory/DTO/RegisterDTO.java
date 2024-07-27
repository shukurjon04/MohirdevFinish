package com.example.factory.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegisterDTO {
    @NotBlank
    @Size(min = 3)
    public String FirstName;
    @NotBlank(message = "incorrect lastname")
    @Size(min = 3)
    public String LastName;
    public double salary;
    @NotBlank
    public String Sharif;
    @NotBlank
    @Size(min = 6)
    public String password;
    @NotBlank
    @Size(min = 4)
    public String username;
    @NotBlank
    public String role;
    @NotBlank
    private String department;
    @NotNull
    private int Age;
    @NotBlank
    @Size(max = 2, min = 2)
    public String Series;
    @NotNull
    public Long Number;
    @NotBlank
    public String JSHSHIR;
    @NotBlank
    public String Nationality;
    @NotBlank
    public List<AdresDTO> adresDTO;
}
