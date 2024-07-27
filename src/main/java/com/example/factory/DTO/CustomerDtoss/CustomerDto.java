package com.example.factory.DTO.CustomerDtoss;

import com.example.factory.DTO.AdresDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDto {
    @NotBlank
    @Size(min = 3)
    public String FirstName;
    @NotBlank(message = "incorrect lastname")
    @Size(min = 3)
    public String LastName;
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
