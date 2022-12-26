package com.example.commonapi.events;

import com.example.commonapi.enums.StateProduct;
import lombok.Getter;

public class ProductCreatedEvent extends BaseEvent<String>{
    @Getter private String name;
    @Getter private String description;
    @Getter private double price;
    @Getter private int quantity;
    @Getter private StateProduct state;
    @Getter private String categoryId;

    public ProductCreatedEvent(String id, String name, String description, double price, int quantity, String categoryId) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.state = state;
        this.categoryId = categoryId;
    }
}
