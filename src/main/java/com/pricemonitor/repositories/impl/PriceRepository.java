package com.pricemonitor.repositories.impl;

import com.pricemonitor.entity.Price;
import com.pricemonitor.hibernate.AbstractRepository;
import com.pricemonitor.repositories.IPriceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PriceRepository extends AbstractRepository implements IPriceRepository {
    public PriceRepository() {
        this.setClazz(Price.class);
    }

    @Override
    public void createPrice(Price price) {
        this.getEntityManager().getTransaction().begin();
        this.create(price);
        this.getEntityManager().getTransaction().commit();

    }

    @Override
    public Price findPriceById(int id) {
        return (Price) findOneById(id);
    }
}
