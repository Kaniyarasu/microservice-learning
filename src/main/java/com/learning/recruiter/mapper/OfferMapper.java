package com.learning.recruiter.mapper;

import com.learning.recruiter.api.Offer;
import com.learning.recruiter.entity.OfferEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper between Offer and OfferEntity
 */
@Component
public class OfferMapper extends BaseMapper<Offer, OfferEntity> {
    public OfferMapper() {
        super(Offer.class, OfferEntity.class);
    }
}