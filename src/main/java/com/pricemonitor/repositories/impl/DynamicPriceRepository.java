package com.pricemonitor.repositories.impl;

import com.pricemonitor.entity.Category;
import com.pricemonitor.entity.DynamicPrice;
import com.pricemonitor.hibernate.AbstractRepository;
import com.pricemonitor.repositories.IDynamicPriceRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class DynamicPriceRepository extends AbstractRepository implements IDynamicPriceRepository {
    public DynamicPriceRepository() {
        this.setClazz(DynamicPrice.class);
    }

    @Transactional
    @Override
    public void createDynamicPrice(DynamicPrice dynamicPrice) {
        this.create(dynamicPrice);
    }

    @Transactional
    @Override
    public DynamicPrice findDynamicPriceById(int id) {
        return (DynamicPrice) this.findOneById(id);
    }

    @Transactional
    @Override
    public List<DynamicPrice> findAllDynamicPriceList() {
        return this.findAll();
    }

    @Transactional
    @Override
    public void deleteDynamicPriceByProductId(int productId) {
        CriteriaBuilder criteriaBuilder = this.getEntityManager().getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<DynamicPrice> query = criteriaBuilder.createQuery(DynamicPrice.class);
        Root<DynamicPrice> root = query.from(DynamicPrice.class);
        query.select(root);
        query.where(criteriaBuilder.equal(root.get("product"), productId));
        DynamicPrice dynamicPrice = this.getEntityManager().getEntityManagerFactory().createEntityManager().createQuery(query).getSingleResult();
        this.delete(dynamicPrice);
    }
}
