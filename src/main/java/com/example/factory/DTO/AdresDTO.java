package com.example.factory.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class AdresDTO {
    @NotBlank
    public String Region;
    @NotBlank
    public String District;
    @NotBlank
    public String Village;
    @NotBlank
    public String Home;
}
