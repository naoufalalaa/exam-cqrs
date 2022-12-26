package ma.enset.inventoryservice.query.repositories;

import ma.enset.inventoryservice.query.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {
    public Category findByName(String name);
}
