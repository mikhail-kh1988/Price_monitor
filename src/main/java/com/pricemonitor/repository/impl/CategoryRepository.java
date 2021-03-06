package com.pricemonitor.repository.impl;

import com.pricemonitor.entity.Category;
import com.pricemonitor.hibernate.AbstractRepository;
import com.pricemonitor.repository.ICategoryRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CategoryRepository extends AbstractRepository implements ICategoryRepository {

    public CategoryRepository() {
        this.setClazz(Category.class);
    }

    @Transactional
    @Override
    public void createCategory(Category category) {
        this.create(category);
    }

    @Transactional
    @Override
    public void updateCategory(Category category) {
        this.update(category);
    }

    @Override
    public void deleteCategory(Category category) {
        this.delete(category);
    }

    @Transactional
    @Override
    public Category findCategoryById(int id) {
        return (Category) this.findOneById(id);
    }

    @Transactional
    @Override
    public Category findCategoryByName(String categoryName) {
        CriteriaBuilder criteriaBuilder = this.getEntityManager().getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<Category> query = criteriaBuilder.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);
        query.select(root);
        query.where(criteriaBuilder.equal(root.get("name"), categoryName));
        return this.getEntityManager().getEntityManagerFactory().createEntityManager().createQuery(query).getSingleResult();
    }

    @Override
    public List<Category> getAllCategory() {
        return this.findAll();
    }
}
