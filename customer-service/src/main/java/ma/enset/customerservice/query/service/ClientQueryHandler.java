package ma.enset.customerservice.query.service;

import com.example.commonapi.events.ClientCreatedEvent;
import com.example.commonapi.events.CustomerDeletedEvent;
import com.example.commonapi.queries.GetAllCustomers;
import com.example.commonapi.queries.GetCustomerById;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.customerservice.query.entities.Client;
import ma.enset.customerservice.query.repositories.ClientRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ClientQueryHandler {
    private ClientRepository clientRepository;

    @EventHandler
    public void on(ClientCreatedEvent event){
        log.info("Client created event received: {}");
        List<Client> clients = clientRepository.findAll();
        Boolean found = false;
        for (Client client : clients) {
            if(client.getEmail().equals(event.getEmail())){
                found = true;
                break;
            }
        }
        if(found){
            return ;
        }
        Client client = new Client();

        client.setId(event.getId());
        client.setName(event.getName());
        client.setEmail(event.getEmail());
        client.setAddress(event.getAddress());
        client.setPhone(event.getPhone());
        clientRepository.save(client);
    }

    @EventHandler
    public void on(CustomerDeletedEvent event){
        log.info("Customer deleted event received: {}");
        clientRepository.deleteById(event.getId());
    }
    @QueryHandler
    public Client on(GetCustomerById query){
        return clientRepository.findById(query.getId()).orElse(null);
    }

    @QueryHandler
    public List<Client> on(GetAllCustomers query){
        return clientRepository.findAll();
    }
}
