package br.com.horys.alex.product.controllers;

import br.com.horys.alex.product.BusinessException;
import br.com.horys.alex.product.models.Product;
import br.com.horys.alex.product.repositories.CategoryRepository;
import br.com.horys.alex.product.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository,
                             CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping()
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Product salvar(@Valid @RequestBody ProductRequest request) {

        var category = categoryRepository.findById(request.categoryId())
            .orElseThrow(() -> new BusinessException("NAO ACHEI ESSA CATEGORIA"));

        var product = new Product(
            request.description(),
            category
        );

        productRepository.save(product);
        return product;
    }

    @PostMapping("/v2")
    @ResponseStatus(HttpStatus.CREATED)
    public Product salvar2(@Valid @RequestBody ProductRequest request) {

        var categoryOptional = categoryRepository.findById(request.categoryId());

        if (categoryOptional.isEmpty()) {
            throw new BusinessException("NAO ACHEI ESSA CATEGORIA");
        }

        var product = new Product(
            request.description(),
            categoryOptional.get()
        );

        productRepository.save(product);
        return product;
    }
}