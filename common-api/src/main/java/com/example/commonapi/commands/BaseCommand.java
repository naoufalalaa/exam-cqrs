package com.example.commonapi.commands;

import lombok.Getter;

public abstract class BaseCommand<T> {
    @Getter
    public T id;

    public BaseCommand(T id) {
        this.id = id;
    }
}
