package ma.enset.inventoryservice.commands.aggregates;

import com.example.commonapi.commands.CreateCustomerCommand;
import com.example.commonapi.commands.CreateProductCommand;
import com.example.commonapi.events.ClientCreatedEvent;
import com.example.commonapi.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String categoryId;

    public ProductAggregate() {
        // Required by Axon
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {
        if (command.getName()== null || command.getName().isEmpty()) {
            throw new IllegalArgumentException("No field can be empty");
        }
        AggregateLifecycle.apply(new ProductCreatedEvent(
                UUID.randomUUID().toString(),
                command.getName(),
                command.getDescription(),
                command.getPrice(),
                command.getQuantity(),
                command.getCategoryId()
        ));
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.price = event.getPrice();
        this.quantity = event.getQuantity();
        this.categoryId = event.getCategoryId();
    }
}
