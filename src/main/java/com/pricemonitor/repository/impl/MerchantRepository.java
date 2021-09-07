package com.pricemonitor.repository.impl;

import com.pricemonitor.entity.Merchant;
import com.pricemonitor.hibernate.AbstractRepository;
import com.pricemonitor.repository.IMerchantRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class MerchantRepository extends AbstractRepository implements IMerchantRepository {

    public MerchantRepository() {
        this.setClazz(Merchant.class);
    }

    @Override
    @Transactional
    public void createMerchant(Merchant merchant) {
        this.create(merchant);
    }

    @Transactional
    @Override
    public Merchant findMerchantById(int id) {
        return (Merchant) this.findOneById(id);
    }

    @Override
    public java.util.List<Merchant> findAllMerchant() {
        return this.findAll();
    }

    @Override
    @Transactional
    public void updateMerchant(Merchant merchant) {
        this.update(merchant);
    }

    @Override
    @Transactional
    public void deleteMerchant(Merchant merchant) {
        this.delete(merchant);
    }
}
