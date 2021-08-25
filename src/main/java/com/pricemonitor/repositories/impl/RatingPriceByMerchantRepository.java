package com.pricemonitor.repositories.impl;

import com.pricemonitor.entity.Merchant;
import com.pricemonitor.entity.Product;
import com.pricemonitor.entity.RatingPriceByMerchant;
import com.pricemonitor.hibernate.AbstractRepository;
import com.pricemonitor.repositories.IRatingPriceByMerchantRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RatingPriceByMerchantRepository extends AbstractRepository implements IRatingPriceByMerchantRepository {
    public RatingPriceByMerchantRepository() {
        this.setClazz(RatingPriceByMerchant.class);
    }

    @Override
    public RatingPriceByMerchant findRatingPriceById(int id) {
        return (RatingPriceByMerchant) this.findOneById(id);
    }

    @Override
    public void createRatingPrice(RatingPriceByMerchant ratingPriceByMerchant) {
        this.getEntityManager().getTransaction().begin();
        this.create(ratingPriceByMerchant);
        this.getEntityManager().getTransaction().commit();
    }

    @Override
    public List<RatingPriceByMerchant> findAllRatingPrice() {
        return this.findAll();
    }

    @Override
    public List<RatingPriceByMerchant> findRatingBetweenMerchant(Merchant merchant1, Merchant merchant2) {
        CriteriaBuilder criteriaBuilder = this.getEntityManager().getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<RatingPriceByMerchant> query = criteriaBuilder.createQuery(RatingPriceByMerchant.class);
        Root<RatingPriceByMerchant> root = query.from(RatingPriceByMerchant.class);
        query.select(root);
        query.where(criteriaBuilder.between(root.get("merchant_id"), merchant1.getId(), merchant2.getId()));
        query.orderBy(criteriaBuilder.desc(root.get("product_id")));
        return (List<RatingPriceByMerchant>) this.getEntityManager().getEntityManagerFactory().createEntityManager().createQuery(query).getSingleResult();
    }
}
