package tech.rent.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.rent.be.dto.CategoryDTO;
import tech.rent.be.dto.RealEstateDTO;
import tech.rent.be.entity.Category;
import tech.rent.be.entity.RealEstate;
import tech.rent.be.repository.CategoryRepository;
import tech.rent.be.repository.RealEstateRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;


    public Category CreateCategory(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setCategoryname(categoryDTO.getCategoryname());
        return categoryRepository.save(category);
    }


    public List<CategoryDTO> getAllCate() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::convertToCategoryDTO)
                .collect(Collectors.toList());
    }
    private CategoryDTO convertToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setCategoryname(category.getCategoryname());
        return categoryDTO;
    }

}
