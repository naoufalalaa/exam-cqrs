package com.example.commonapi.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

public class ClientCreatedEvent extends BaseEvent<String> {
    @Getter private String name;
    @Getter private String email;
    @Getter private String phone;
    @Getter private String address;

    public ClientCreatedEvent(String id, String name, String email, String phone, String address) {
        super(id);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}
