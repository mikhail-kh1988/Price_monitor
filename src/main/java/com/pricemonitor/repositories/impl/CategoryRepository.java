package com.pricemonitor.repositories.impl;

import com.pricemonitor.entity.Category;
import com.pricemonitor.hibernate.AbstractRepository;
import com.pricemonitor.repositories.ICategoryRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CategoryRepository extends AbstractRepository implements ICategoryRepository {

    public CategoryRepository() {
        this.setClazz(Category.class);
    }

    @Override
    @Transactional
    public void createCategory(Category category) {
        this.create(category);
    }

    @Override
    public Category findCategoryById(int id) {
        return (Category) this.findOneById(id);
    }

    @Override
    public List<Category> getAllCategory() {
        return this.findAll();
    }
}
