package com.example.commonapi.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public abstract class BaseCommand<T> {
    @Getter
    @TargetAggregateIdentifier
    public T id;

    public BaseCommand(T id) {
        this.id = id;
    }
}
