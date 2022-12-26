package ma.enset.orderservice.query.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class LigneCommand {
    @Id
    private String id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Product product;
    private int quantity;
}
