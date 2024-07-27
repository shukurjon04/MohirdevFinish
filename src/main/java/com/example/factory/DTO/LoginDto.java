package com.example.factory.DTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginDto {
    String username;
    String password;
}
