package com.projektowanie.service;

import com.projektowanie.exception.CatalogNotFoundException;
import com.projektowanie.model.Catalog;
import com.projektowanie.repository.CatalogRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {
    private final CatalogRepository catalogRepository;

    public CatalogService(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    public void saveCatalog(Catalog catalog) {
        catalogRepository.save(catalog);
    }

    public void deleteCatalog(Long id) {
        catalogRepository.deleteById(id);
    }

    public Catalog getCatalog(Long id) {
        return catalogRepository.findById(id)
                .orElseThrow(() -> new CatalogNotFoundException(
                        String.format("Catalog with id=%s was not found!", id)
                ));
    }

    @Transactional
    public void updateCatalog(Catalog updatedCatalog) {
        var catalog = getCatalog(updatedCatalog.getCatalogId());
        catalog.setName(updatedCatalog.getName());
        catalog.setProducts(updatedCatalog.getProducts());
        catalog.setLastModificationDate(updatedCatalog.getLastModificationDate());
    }
}
