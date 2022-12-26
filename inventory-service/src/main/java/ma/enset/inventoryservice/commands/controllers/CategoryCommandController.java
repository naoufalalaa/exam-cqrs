package ma.enset.inventoryservice.commands.controllers;

import com.example.commonapi.commands.CreateCategoryCommand;
import com.example.commonapi.commands.CreateProductCommand;
import com.example.commonapi.commands.DeleteCategoryCommand;
import com.example.commonapi.commands.UpdateCategoryCommand;
import com.example.commonapi.dtos.CreateCategoryRequestDTO;
import com.example.commonapi.dtos.CreateProductRequestDTO;
import com.example.commonapi.dtos.UpdateCategoryRequestDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/command/category")
public class CategoryCommandController {
    private CommandGateway commandGateway;

    public CategoryCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping(path = "/create")
    public CompletableFuture<String> createCategory(@RequestBody CreateCategoryRequestDTO createCategoryDTO) {
        return commandGateway.send(new CreateCategoryCommand(
                UUID.randomUUID().toString(),
                createCategoryDTO.getName(),
                createCategoryDTO.getDescription()
        ));
    }

    @PutMapping(path = "/update")
    public CompletableFuture<String> updateCategory(@RequestBody UpdateCategoryRequestDTO updateCategoryRequestDTO) {
        return commandGateway.send(new UpdateCategoryCommand(
                updateCategoryRequestDTO.getId(),
                updateCategoryRequestDTO.getName(),
                updateCategoryRequestDTO.getDescription()
        ));
    }

    @DeleteMapping(path = "/delete/{id}")
    public CompletableFuture<String> deleteCategory(@PathVariable String id) {
        return commandGateway.send(new DeleteCategoryCommand(
                id
        ));
    }
}
