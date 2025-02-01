package hu.me.iit.todolistapp.services;

import hu.me.iit.todolistapp.dtos.CategoryDto;
import hu.me.iit.todolistapp.entities.Category;
import hu.me.iit.todolistapp.exceptions.AppException;
import hu.me.iit.todolistapp.mappers.CategoryMapper;
import hu.me.iit.todolistapp.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toCategoryDtos(categories);
    }

    public CategoryDto getCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException("Category not found", HttpStatus.NOT_FOUND));
        return categoryMapper.toCategoryDto(category);
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category category= categoryMapper.toCategory(categoryDto);
        Category storedCategory = categoryRepository.findById(id).orElseThrow(() -> new AppException("Category not found", HttpStatus.NOT_FOUND));
        category.setId(storedCategory.getId());
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.toCategoryDto(updatedCategory);
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.toCategory(categoryDto);
        Category createdCategory = categoryRepository.save(category);
        return categoryMapper.toCategoryDto(createdCategory);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
