package ma.enset.inventoryservice.query.service;

import com.example.commonapi.enums.StateProduct;
import com.example.commonapi.events.CategoryCreatedEvent;
import com.example.commonapi.events.ProductCreatedEvent;
import com.example.commonapi.queries.GetAllCategories;
import com.example.commonapi.queries.GetCategoryByName;
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
public class CategoryQueryHandler {
    private CategoryRepository categoryRepository;

    @EventHandler
    public void on(CategoryCreatedEvent event){
        if(event.getId() != null){
            log.info("Category created event received: {}");
            Category category = new Category();
            category.setId(event.getId());
            category.setName(event.getName());
            category.setDescription(event.getDescription());
            categoryRepository.save(category);
        }
    }
    @QueryHandler
    public Category on(GetCategoryByName query){
        return categoryRepository.findById(query.getName()).orElse(null);
    }

    @QueryHandler
    public List<Category> on(GetAllCategories query){
        return categoryRepository.findAll();
    }
}
