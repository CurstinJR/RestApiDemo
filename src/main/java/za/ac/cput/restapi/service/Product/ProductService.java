package za.ac.cput.restapi.service.Product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import za.ac.cput.restapi.entity.Product.Product;
import za.ac.cput.restapi.entity.Product.ProductCategory;
import za.ac.cput.restapi.entity.Product.ProductInventory;
import za.ac.cput.restapi.repository.Product.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService
{
    private ProductRepository productRepository;
    private ProductCategoryService productCategoryService;
    private ProductInventoryService productInventoryService;

    public Product getById(Long id)
    {
        return productRepository.findById(id).orElseThrow();
    }

    public List<Product> getAll()
    {
        return productRepository.findAll();
    }

    public Product addProduct(Product product)
    {
        product.setCreatedAt(LocalDateTime.now());

        if (product.getProductCategory() != null)
        {
            product.setProductCategory(productCategoryService.getProductCategory(product.getProductCategory()));
        }

        if (product.getProductInventory() != null)
        {
            product.setProductInventory(productInventoryService.addProductInventory(product.getProductInventory()));
        }

        return productRepository.save(product);
    }

    public Product updateProduct(Product newProduct, Long id)
    {
        return productRepository.findById(id).map(product ->
        {
            String name = newProduct.getName() != null ?
                    newProduct.getName() :
                    product.getName();

            String description = newProduct.getDescription() != null ?
                    newProduct.getDescription() :
                    product.getDescription();

            BigDecimal price = newProduct.getPrice() != null ?
                    newProduct.getPrice() :
                    product.getPrice();

            ProductCategory productCategory = newProduct.getProductCategory() != null ?
                    productCategoryService.getProductCategory(newProduct.getProductCategory()) :
                    product.getProductCategory();

            ProductInventory productInventory = newProduct.getProductInventory() != null ?
                    productInventoryService.updateQuantity(product.getId(), newProduct.getProductInventory()) :
                    product.getProductInventory();

            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setProductCategory(productCategory);
            product.setProductInventory(productInventory);
            product.setModifiedAt(LocalDateTime.now());

            return productRepository.save(product);
        }).orElseGet(() ->
        {
            newProduct.setId(id);
            return productRepository.save(newProduct);
        });
    }

    public void deleteProductById(long id)
    {
        productRepository.deleteById(id);
    }
}
