package com.pricemonitor.repositories.impl;

import com.pricemonitor.entity.DynamicPrice;
import com.pricemonitor.hibernate.AbstractRepository;
import com.pricemonitor.repositories.IDynamicPriceRepository;
import org.springframework.stereotype.Repository;

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
}
