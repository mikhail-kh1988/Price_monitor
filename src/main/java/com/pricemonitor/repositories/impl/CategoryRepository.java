package com.pricemonitor.repositories.impl;

import com.pricemonitor.entity.Category;
import com.pricemonitor.entity.Product;
import com.pricemonitor.hibernate.AbstractRepository;
import com.pricemonitor.repositories.ICategoryRepository;
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

    @Override
    @Transactional
    public void createCategory(Category category) {
        this.create(category);
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
