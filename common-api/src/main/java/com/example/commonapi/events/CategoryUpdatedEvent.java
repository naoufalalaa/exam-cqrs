package com.example.commonapi.events;

import lombok.Getter;

public class CategoryUpdatedEvent extends BaseEvent<String>{
    @Getter
    private String name;
    @Getter
    private String description;

    public CategoryUpdatedEvent(String id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }
}
