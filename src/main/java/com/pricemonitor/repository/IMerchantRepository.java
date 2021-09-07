package com.pricemonitor.repository;

import com.pricemonitor.entity.Merchant;
import com.pricemonitor.hibernate.CRUDRepository;

public interface IMerchantRepository extends CRUDRepository {

    void createMerchant(Merchant merchant);
    Merchant findMerchantById(int id);
    java.util.List<Merchant> findAllMerchant();
    void updateMerchant(Merchant merchant);
    void deleteMerchant(Merchant merchant);
}
