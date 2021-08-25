package com.pricemonitor.repositories;

import com.pricemonitor.entity.Category;
import com.pricemonitor.hibernate.CRUDRepository;

public interface ICategoryRepository extends CRUDRepository {

    void createCategory(Category category);
    Category findCategoryById(int id);
    java.util.List<Category> getAllCategory();
}
