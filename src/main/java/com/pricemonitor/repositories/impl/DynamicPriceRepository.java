package com.pricemonitor.repositories.impl;

import com.pricemonitor.entity.DynamicPrice;
import com.pricemonitor.hibernate.AbstractRepository;
import com.pricemonitor.repositories.IDynamicPriceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DynamicPriceRepository extends AbstractRepository implements IDynamicPriceRepository {
    public DynamicPriceRepository() {
        this.setClazz(DynamicPrice.class);
    }

    @Override
    public void createDynamicPrice(DynamicPrice dynamicPrice) {
        this.getEntityManager().getTransaction().begin();
        this.create(dynamicPrice);
        this.getEntityManager().getTransaction().commit();
    }

    @Override
    public DynamicPrice findDynamicPriceById(int id) {
        return (DynamicPrice) this.findOneById(id);
    }

    @Override
    public List<DynamicPrice> findAllDynamicPriceList() {
        return this.findAll();
    }
}
