package ma.enset.orderservice.query.controllers;

import com.example.commonapi.queries.GetAllCustomers;
import com.example.commonapi.queries.GetCustomerById;
import ma.enset.customerservice.query.entities.Client;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query/customers")
public class CustomerQueryController {
    private QueryGateway queryGateway;

    public CustomerQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(path = "/all")
    public List<Client> getAllCustomers() {
        return queryGateway.query(new GetAllCustomers(), ResponseTypes.multipleInstancesOf(Client.class)).join();
    }
    @GetMapping(path = "/{id}")
    public Client getCustomerById(@PathVariable String id) {
        return queryGateway.query(new GetCustomerById(id),ResponseTypes.instanceOf(Client.class)).join();
    }
}
