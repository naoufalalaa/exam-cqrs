package com.example.commonapi.commands;

import lombok.Getter;

public class CreateCustomerCommand extends BaseCommand<String> {
    @Getter public String name;
    @Getter public String email;
    @Getter public String phone;
    @Getter public String address;

    public CreateCustomerCommand(String id, String name, String email, String phone, String address) {
        super(id);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}
