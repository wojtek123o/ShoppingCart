package com.projektowanie.service;

import com.projektowanie.exception.ProductNotFoundException;
import com.projektowanie.model.Product;
import com.projektowanie.repository.CategoryRepository;
import com.projektowanie.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllProductsFromCatalog(Long catalogId) {
        return productRepository.findAllByCatalogCatalogId(catalogId);
    }

    @Transactional
    public void saveProduct(Product product) {
        var category = product.getCategory();
        var categoryFromDb = categoryRepository.findByName(category.getName());
        category = categoryFromDb.orElseGet(() -> categoryRepository.save(product.getCategory()));
        product.setCategory(category);
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
        var product = getProduct(updatedProduct.getProductId());
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
