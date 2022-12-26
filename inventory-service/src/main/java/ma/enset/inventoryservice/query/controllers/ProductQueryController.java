package ma.enset.inventoryservice.query.controllers;

import com.example.commonapi.queries.GetAllProducts;
import com.example.commonapi.queries.GetProductById;
import ma.enset.inventoryservice.query.entities.Product;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query/product")
public class ProductQueryController {
    private QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(path = "/all")
    public List<Product> getAllProducts() {
        return queryGateway.query(new GetAllProducts(), ResponseTypes.multipleInstancesOf(Product.class)).join();
    }
    @GetMapping(path = "/{name}")
    public Product getProductById(@PathVariable String id) {
        return queryGateway.query(new GetProductById(id),ResponseTypes.instanceOf(Product.class)).join();
    }
}
