package ma.enset.orderservice.commands.aggregates;

import com.example.commonapi.commands.CreateCustomerCommand;
import com.example.commonapi.commands.DeleteCustomerCommand;
import com.example.commonapi.events.ClientCreatedEvent;
import com.example.commonapi.events.CustomerDeletedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class ClientAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;

    public ClientAggregate() {
        // Required by Axon
    }

    @CommandHandler
    public ClientAggregate(CreateCustomerCommand command) {
        if (command.getName() == null || command.getName().isEmpty() || command.getEmail() == null || command.getEmail().isEmpty() || command.getPhone() == null || command.getPhone().isEmpty() || command.getAddress() == null || command.getAddress().isEmpty()) {
            throw new IllegalArgumentException("No field can be empty");
        }
        AggregateLifecycle.apply(new ClientCreatedEvent(
                UUID.randomUUID().toString(),
                command.getName(),
                command.getEmail(),
                command.getPhone(),
                command.getAddress()));
    }

    @EventSourcingHandler
    public void on(ClientCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.email = event.getEmail();
        this.phone = event.getPhone();
        this.address = event.getAddress();
    }

    @CommandHandler
    public void on(DeleteCustomerCommand command) {
        AggregateLifecycle.apply(new CustomerDeletedEvent(command.getId()));
    }

    @EventSourcingHandler
    public void on(CustomerDeletedEvent event) {
        AggregateLifecycle.markDeleted();
    }
}
