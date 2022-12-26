package ma.enset.inventoryservice.query.service;

import com.example.commonapi.enums.StateProduct;
import com.example.commonapi.events.ClientCreatedEvent;
import com.example.commonapi.events.ProductCreatedEvent;
import com.example.commonapi.queries.GetAllProducts;
import com.example.commonapi.queries.GetProductById;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.inventoryservice.query.entities.Category;
import ma.enset.inventoryservice.query.entities.Product;
import ma.enset.inventoryservice.query.repositories.CategoryRepository;
import ma.enset.inventoryservice.query.repositories.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductQueryHandler {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @EventHandler
    public void on(ProductCreatedEvent event){
        log.info("Product created event received: {}");
        Product product = new Product();
        product.setId(event.getId());
        product.setName(event.getName());
        product.setDescription(event.getDescription());
        product.setPrice(event.getPrice());
        product.setQuantity(event.getQuantity());
        Category category = categoryRepository.findById(event.getCategoryId()).orElse(null);
        if (category != null){
            product.setCategory(category);
        }else{
            Category category1 = categoryRepository.findByName("default");
            if (category1 == null) {
                category1 = new Category();
                category1.setName("default");
                category1.setDescription("default");
                categoryRepository.save(category1);

            }
            product.setCategory(category1);
        }
        if(event.getQuantity() > 0)
            product.setState(StateProduct.Disponible);
        else
            product.setState(StateProduct.Production);
        productRepository.save(product);
    }

    @QueryHandler
    public Product on(GetProductById query){
        return productRepository.findById(query.getId()).orElse(null);
    }
    @QueryHandler
    public List<Product> on(GetAllProducts query){
        return productRepository.findAll();
    }
}
