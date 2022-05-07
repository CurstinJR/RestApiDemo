package za.ac.cput.restapi.repository.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.restapi.entity.Product.ProductCategory;

import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>
{
    Optional<ProductCategory> findByName(String name);
}
