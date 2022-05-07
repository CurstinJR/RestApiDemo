package za.ac.cput.restapi.service.Product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import za.ac.cput.restapi.entity.Product.ProductCategory;
import za.ac.cput.restapi.repository.Product.ProductCategoryRepository;

@Service
@AllArgsConstructor
public class ProductCategoryService
{
    private ProductCategoryRepository productCategoryRepository;

    public ProductCategory getProductCategory(ProductCategory productCategory)
    {
        String name = productCategory.getName();
        return productCategoryRepository.findByName(name).orElseThrow();
    }
}
