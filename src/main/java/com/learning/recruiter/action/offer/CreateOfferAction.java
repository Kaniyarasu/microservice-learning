package com.learning.recruiter.action.offer;

import com.learning.recruiter.api.Offer;
import com.learning.recruiter.entity.OfferEntity;
import com.learning.recruiter.mapper.OfferMapper;
import com.learning.recruiter.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Action to create and validate the offer
 */
@Component
public class CreateOfferAction {

    @Autowired
    private OfferMapper offerMapper;

    @Autowired
    private OfferRepository offerRepository;

    /**
     * Action method for create offer
     * @param offer - New Offer
     * @return Offer - new object
     */
    public Offer invoke(Offer offer) {
        OfferEntity offerEntity =
                offerRepository.createOffer(
                        offerMapper.mapToEntity(offer));

        return offerMapper.mapFromEntity(offerEntity);
    }
}
