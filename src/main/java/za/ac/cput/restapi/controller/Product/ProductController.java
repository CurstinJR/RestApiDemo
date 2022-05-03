package za.ac.cput.restapi.controller.Product;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.restapi.entity.Product.Product;
import za.ac.cput.restapi.service.Product.ProductService;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController
{
    private ProductService productService;
    private ProductModelAssembler productModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Product>> getAll()
    {
        return productModelAssembler.toCollectionModel(productService.getAll());
    }

    @GetMapping("/{id}")
    public EntityModel<Product> getById(@PathVariable Long id)
    {
        return productModelAssembler.toModel(productService.getById(id));
    }
}
