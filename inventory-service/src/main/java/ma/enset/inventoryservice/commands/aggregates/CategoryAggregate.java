package ma.enset.inventoryservice.commands.aggregates;

import com.example.commonapi.commands.CreateCategoryCommand;
import com.example.commonapi.commands.CreateCustomerCommand;
import com.example.commonapi.commands.CreateProductCommand;
import com.example.commonapi.events.CategoryCreatedEvent;
import com.example.commonapi.events.ClientCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class CategoryAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private String description;

    public CategoryAggregate() {
        // Required by Axon
    }

    @CommandHandler
    public CategoryAggregate(CreateCategoryCommand command) {
        if (command.getName() == null || command.getName().isEmpty() || command.getDescription() == null || command.getDescription().isEmpty()) {
            throw new IllegalArgumentException("No field can be empty");
        }
        AggregateLifecycle.apply(new CategoryCreatedEvent(
                UUID.randomUUID().toString(),
                command.getName(),
                command.getDescription()));
    }
    @EventSourcingHandler
    public void on(CategoryCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
    }
}
