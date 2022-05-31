package com.app.library.service;

import com.app.library.model.Category;
import com.app.library.repository.BookRepository;
import com.app.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BookRepository bookRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    public Optional<Category> findByName(String name) {
        return  categoryRepository.findByName(name);
    }

    public Integer countBookByCategoryId( Long categoryId) {

        return bookRepository.countByCategoryId(categoryId);
    }
}
