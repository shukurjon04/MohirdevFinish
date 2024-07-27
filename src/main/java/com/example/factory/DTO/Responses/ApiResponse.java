package com.example.factory.DTO.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {

    private String description;
    private boolean success;
    private int code;
    private Object data;
}
