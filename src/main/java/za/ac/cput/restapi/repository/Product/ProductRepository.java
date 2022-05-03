package za.ac.cput.restapi.repository.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.restapi.entity.Product.Product;

public interface ProductRepository extends JpaRepository<Product, Long>
{
}
