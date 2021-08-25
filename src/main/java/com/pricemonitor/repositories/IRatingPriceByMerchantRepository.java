package com.pricemonitor.repositories;

import com.pricemonitor.entity.Merchant;
import com.pricemonitor.entity.RatingPriceByMerchant;
import com.pricemonitor.hibernate.CRUDRepository;

public interface IRatingPriceByMerchantRepository extends CRUDRepository {

    RatingPriceByMerchant findRatingPriceById(int id);
    void createRatingPrice(RatingPriceByMerchant ratingPriceByMerchant);
    java.util.List<RatingPriceByMerchant> findAllRatingPrice();
    java.util.List<RatingPriceByMerchant> findRatingBetweenMerchant(Merchant merchant1, Merchant merchant2);

}
