package ma.enset.orderservice.query.repositories;
import ma.enset.orderservice.query.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
