package com.pricemonitor.repositories;

import com.pricemonitor.entity.Price;
import com.pricemonitor.hibernate.CRUDRepository;

public interface IPriceRepository extends CRUDRepository {

    void createPrice(Price price);
    Price findPriceById(int id);

}
