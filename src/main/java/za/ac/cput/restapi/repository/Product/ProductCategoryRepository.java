package za.ac.cput.restapi.repository.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.restapi.entity.Product.ProductCategory;
import za.ac.cput.restapi.entity.Product.ProductInventory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>
{
}
