package com.example.commonapi.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UpdateCategoryCommand extends BaseCommand<String> {
    @Getter public String categoryId;
    @Getter private String name;
    @Getter private String description;

    public UpdateCategoryCommand(String id, String categoryId, String name, String description) {
        super(id);
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }
}
