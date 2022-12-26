package com.example.commonapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CreateCustomerRequestDTO {
    private String name;
    private String email;
    private String phone;
    private String address;
}
