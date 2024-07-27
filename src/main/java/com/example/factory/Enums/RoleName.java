package com.example.factory.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum RoleName {
    Director("Director","Company boss"),
    Foreman("Foreman","manage action"),
    Staff("Employee","Worker");

    private String name;
    private String Description;
}
