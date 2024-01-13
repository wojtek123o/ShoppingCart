package com.projektowanie.service;

import com.projektowanie.exception.ProductNotFoundException;
import com.projektowanie.model.Product;
import com.projektowanie.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllProductsFromCatalog(Long catalogId) {
        return productRepository.findAllByCatalogId(catalogId);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Product with id=%s was not found!", id)
                ));
    }

    @Transactional
    public void updateProduct(Product updatedProduct) {
        var product = getProduct(updatedProduct.getId());
        product.setCatalog(updatedProduct.getCatalog());
        product.setCategory(updatedProduct.getCategory());
        product.setPrice(updatedProduct.getPrice());
        product.setImage(updatedProduct.getImage());
        product.setDescription(updatedProduct.getDescription());
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
