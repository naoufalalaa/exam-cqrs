package com.example.commonapi.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UpdateCategoryCommand extends BaseCommand<String> {
    @Getter private String name;
    @Getter private String description;

    public UpdateCategoryCommand(String id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }
}
