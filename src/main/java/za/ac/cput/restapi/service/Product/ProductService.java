package za.ac.cput.restapi.service.Product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import za.ac.cput.restapi.entity.Product.Product;
import za.ac.cput.restapi.repository.Product.ProductRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService
{
    private ProductRepository productRepository;
    
    public Product getById(Long id)
    {
        return productRepository.findById(id).orElseThrow();
    }

    public List<Product> getAll()
    {
        return productRepository.findAll();
    }
}
