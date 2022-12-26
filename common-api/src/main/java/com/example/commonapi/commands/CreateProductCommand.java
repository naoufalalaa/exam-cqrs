package com.example.commonapi.commands;

import lombok.Getter;

public class CreateProductCommand extends BaseCommand<String> {
    @Getter private String name;
    @Getter private String description;
    @Getter private double price;
    @Getter private int quantity;
    @Getter private String categoryId;

    public CreateProductCommand(String id, String name, String description, double price, int quantity, String categoryId) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
    }
}
