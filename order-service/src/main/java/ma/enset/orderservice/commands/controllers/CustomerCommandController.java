package ma.enset.orderservice.commands.controllers;

import com.example.commonapi.commands.CreateCustomerCommand;
import com.example.commonapi.commands.DeleteCustomerCommand;
import com.example.commonapi.dtos.CreateCustomerRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/command/customers")
public class CustomerCommandController {
    private CommandGateway commandGateway;

    public CustomerCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping(path = "/create")
    public CompletableFuture<String> createCustomer(@RequestBody CreateCustomerRequestDTO createCustomerRequestDTO) {
        CompletableFuture<String> response = commandGateway.send(new CreateCustomerCommand(
                UUID.randomUUID().toString(),
                createCustomerRequestDTO.getName(),
                createCustomerRequestDTO.getEmail(),
                createCustomerRequestDTO.getPhone(),
                createCustomerRequestDTO.getAddress()
        ));
        return response;
    }

    @DeleteMapping(path = "/delete/{id}")
    public CompletableFuture<String> deleteCustomer(@PathVariable String id) {
        CompletableFuture<String> response = commandGateway.send(new DeleteCustomerCommand(id));
        return response;
    }
}
