package hu.me.iit.todolistapp.mappers;

import hu.me.iit.todolistapp.dtos.CategoryDto;
import hu.me.iit.todolistapp.entities.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryDto categoryDto);
    CategoryDto toCategoryDto(Category category);
    List<CategoryDto> toCategoryDtos(List<Category> categories);

}
