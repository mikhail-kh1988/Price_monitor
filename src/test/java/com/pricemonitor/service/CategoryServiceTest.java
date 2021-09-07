package com.pricemonitor.service;

import com.pricemonitor.ContextConfigurationTest;
import com.pricemonitor.JPAConfigureTest;
import com.pricemonitor.dto.CategoryDTO;
import com.pricemonitor.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ContextConfigurationTest.class, JPAConfigureTest.class})
class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Autowired
    @Qualifier("categoryRepository")
    private ICategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        categoryService = mock(CategoryService.class);
    }

    @Test
    void whenAddInRepositoryNewCategory() {
        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryName("from test category");
        dto.setDescription("test description");

        int countCategoryBeforeInsert = categoryRepository.getAllCategory().size();

        categoryService.createNewCategory(dto);

        assertEquals(countCategoryBeforeInsert, categoryRepository.getAllCategory().size());

    }

}