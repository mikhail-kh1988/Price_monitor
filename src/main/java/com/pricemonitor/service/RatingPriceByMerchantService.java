package com.pricemonitor.service;

import com.pricemonitor.entity.Category;
import com.pricemonitor.entity.Merchant;
import com.pricemonitor.entity.Product;
import com.pricemonitor.entity.RatingPriceByMerchant;
import com.pricemonitor.repositories.IRatingPriceByMerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RatingPriceByMerchantService {

    @Autowired
    private IRatingPriceByMerchantRepository ratingPriceByMerchantRepository;

    public void createRating(RatingPriceByMerchant ratingPriceByMerchant){
        ratingPriceByMerchantRepository.createRatingPrice(ratingPriceByMerchant);
    }

    public java.util.List<RatingPriceByMerchant> getAllRatingPriceByMerchantList(){
        return ratingPriceByMerchantRepository.findAllRatingPrice();
    }

    public java.util.List<RatingPriceByMerchant> getListRatingBetweenMerchant(Merchant merchant1, Merchant merchant2){
        return ratingPriceByMerchantRepository.findRatingBetweenMerchant(merchant1, merchant2);
    }

    public RatingPriceByMerchant findRatingPriceById(int id){
        return ratingPriceByMerchantRepository.findRatingPriceById(id);
    }

    public java.util.List<RatingPriceByMerchant> findRatingByProduct(Product product){
        java.util.List<RatingPriceByMerchant> tempList = new ArrayList<>();
        java.util.List<RatingPriceByMerchant> list = ratingPriceByMerchantRepository.findAllRatingPrice();
        for (RatingPriceByMerchant rpm : list) {
            if (rpm.getProduct().equals(product)){
                tempList.add(rpm);
            }
        }
        return tempList;
    }

    public java.util.List<RatingPriceByMerchant> findRatingByCategory(Category category){
        java.util.List<RatingPriceByMerchant> tempList = new ArrayList<>();
        java.util.List<RatingPriceByMerchant> list = ratingPriceByMerchantRepository.findAllRatingPrice();
        for (RatingPriceByMerchant rpm: list) {
            if (rpm.getCategory().equals(category)){
                tempList.add(rpm);
            }
        }
        return tempList;
    }

}
