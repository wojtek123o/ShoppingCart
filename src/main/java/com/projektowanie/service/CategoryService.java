package com.projektowanie.service;

import com.projektowanie.exception.CategoryNotFoundException;
import com.projektowanie.model.Category;
import com.projektowanie.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(
                        String.format("Category with id=%s does not exist!", categoryId)
                ));
    }

    @Transactional
    public void updateCategory(Category updatedCategory) {
        var category = getCategory(updatedCategory.getCategoryId());
        category.setName(category.getName());
    }

    public Category getCategory(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException(
                        String.format("Category with name=%s does not exist!", name)
                ));
    }
}
