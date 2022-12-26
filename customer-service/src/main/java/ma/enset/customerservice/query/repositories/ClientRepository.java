package ma.enset.customerservice.query.repositories;

import ma.enset.customerservice.query.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,String> {
}
