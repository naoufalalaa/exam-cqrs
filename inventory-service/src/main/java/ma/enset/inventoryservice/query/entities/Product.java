package ma.enset.inventoryservice.query.entities;

import com.example.commonapi.enums.StateProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private StateProduct state;
    @ManyToOne
    private Category category;
}
