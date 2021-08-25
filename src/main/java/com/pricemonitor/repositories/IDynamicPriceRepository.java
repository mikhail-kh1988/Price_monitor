package com.pricemonitor.repositories;

import com.pricemonitor.entity.DynamicPrice;
import com.pricemonitor.hibernate.CRUDRepository;

public interface IDynamicPriceRepository extends CRUDRepository {
    void createDynamicPrice(DynamicPrice dynamicPrice);
    DynamicPrice findDynamicPriceById(int id);
    java.util.List<DynamicPrice> findAllDynamicPriceList();
}
