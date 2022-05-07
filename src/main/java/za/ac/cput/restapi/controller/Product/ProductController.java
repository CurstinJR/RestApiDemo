package za.ac.cput.restapi.controller.Product;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product)
    {
        EntityModel<Product> productEntityModel = productModelAssembler.toModel(productService.addProduct(product));

        return ResponseEntity
                .created(productEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(productEntityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable Long id)
    {
        EntityModel<Product> productEntityModel = productModelAssembler
                .toModel(productService.updateProduct(product, id));

        return ResponseEntity
                .created(productEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(productEntityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id)
    {
        productService.deleteProductById(id);

        return ResponseEntity.noContent().build();
    }
}
