package com.pricemonitor.repositories.impl;

import com.pricemonitor.entity.Category;
import com.pricemonitor.entity.Product;
import com.pricemonitor.entity.User;
import com.pricemonitor.hibernate.AbstractRepository;
import com.pricemonitor.repositories.IProductRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ProductRepository extends AbstractRepository implements IProductRepository {

    public ProductRepository() {
        this.setClazz(Product.class);
    }

    @Transactional
    @Override
    public void createProduct(Product product) {
        this.create(product);

    }

    @Transactional
    @Override
    public Product findProductById(int id) {
        return (Product) this.findOneById(id);
    }

    @Transactional
    @Override
    public List<Product> findProductByCategory(Category category) {
        CriteriaBuilder criteriaBuilder = this.getEntityManager().getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root);
        query.where(criteriaBuilder.equal(root.get("category_id"), category.getId()));
        return (List<Product>) this.getEntityManager().getEntityManagerFactory().createEntityManager().createQuery(query).getSingleResult();
    }

    @Transactional
    @Override
    public List<Product> findProductByBoxing(String boxing) {
        CriteriaBuilder criteriaBuilder = this.getEntityManager().getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<Product> query = criteriaBuilder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root);
        query.where(criteriaBuilder.equal(root.get("boxing"), boxing));
        return (List<Product>) this.getEntityManager().getEntityManagerFactory().createEntityManager().createQuery(query).getSingleResult();
    }

    @Transactional
    @Override
    public List<Product> findAllProduct() {
        return this.findAll();
    }

    @Transactional
    @Override
    public void removeProduct(Product product) {
        this.delete(product);
    }

    @Transactional
    @Override
    public void updateProduct(Product product) {
        this.update(product);

    }
}
