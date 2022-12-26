package ma.enset.inventoryservice.query.controllers;

import com.example.commonapi.queries.GetAllCategories;
import com.example.commonapi.queries.GetAllCustomers;
import com.example.commonapi.queries.GetCategoryByName;
import com.example.commonapi.queries.GetCustomerById;
import ma.enset.inventoryservice.query.entities.Category;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query/category")
public class CategoryQueryController {
    private QueryGateway queryGateway;

    public CategoryQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping(path = "/all")
    public List<Category> getAllCategories() {
        return queryGateway.query(new GetAllCategories(), ResponseTypes.multipleInstancesOf(Category.class)).join();
    }
    @GetMapping(path = "/{name}")
    public Category getCategoryByName(@PathVariable String name) {
        return queryGateway.query(new GetCategoryByName(name),ResponseTypes.instanceOf(Category.class)).join();
    }
}
