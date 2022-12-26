package ma.enset.inventoryservice.commands.controllers;

import com.example.commonapi.commands.CreateCustomerCommand;
import com.example.commonapi.commands.CreateProductCommand;
import com.example.commonapi.dtos.CreateCustomerRequestDTO;
import com.example.commonapi.dtos.CreateProductRequestDTO;
import ma.enset.inventoryservice.query.entities.Product;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/command/products")
public class ProductCommandController {
    private CommandGateway commandGateway;

    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping(path = "/create")
    public CompletableFuture<String> createCustomer(@RequestBody CreateProductRequestDTO createProductRequestDTO) {
        return commandGateway.send(new CreateProductCommand(
                UUID.randomUUID().toString(),
                createProductRequestDTO.getName(),
                createProductRequestDTO.getDescription(),
                createProductRequestDTO.getPrice(),
                createProductRequestDTO.getQuantity(),
                createProductRequestDTO.getCategoryId()
        ));
    }
}
