package com.pricemonitor.repository.impl;

import com.pricemonitor.entity.Price;
import com.pricemonitor.hibernate.AbstractRepository;
import com.pricemonitor.repository.IPriceRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class PriceRepository extends AbstractRepository implements IPriceRepository {
    public PriceRepository() {
        this.setClazz(Price.class);
    }

    @Transactional
    @Override
    public void createPrice(Price price) {
        this.create(price);

    }

    @Override
    public Price findPriceById(int id) {
        return (Price) findOneById(id);
    }
}
