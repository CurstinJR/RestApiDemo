package za.ac.cput.restapi.service.Product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import za.ac.cput.restapi.entity.Product.ProductInventory;
import za.ac.cput.restapi.repository.Product.ProductInventoryRepository;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ProductInventoryService
{
    private ProductInventoryRepository productInventoryRepository;

    public ProductInventory addProductInventory(ProductInventory productInventory)
    {
        productInventory.setCreatedAt(LocalDateTime.now());
        return productInventoryRepository.save(productInventory);
    }

    public ProductInventory getProductInventoryById(long id)
    {
        return productInventoryRepository
                .findById(id)
                .orElseGet(() -> addProductInventory(new ProductInventory()));
    }

    public ProductInventory updateQuantity(long id, ProductInventory productInventory)
    {
        ProductInventory existProductInventory = getProductInventoryById(id);
        existProductInventory.setQuantity(productInventory.getQuantity());
        existProductInventory.setModifiedAt(LocalDateTime.now());
        return productInventoryRepository.save(existProductInventory);
    }
}
