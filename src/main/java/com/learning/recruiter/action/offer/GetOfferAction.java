package com.learning.recruiter.action.offer;

import com.learning.recruiter.api.Offer;
import com.learning.recruiter.mapper.OfferMapper;
import com.learning.recruiter.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Action to get the offer
 */
@Component
public class GetOfferAction
{

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private OfferMapper offerMapper;

    @Autowired
    private GetOfferApplicationsAction getOfferApplicationsAction;

    public Offer invoke(String offerId) {
        Offer offer = offerMapper.mapFromEntity(offerRepository.findOne(offerId));
        offer.setNumberOfApplicants(
                getOfferApplicationsAction.invoke(offerId).size());

        return offer;
    }
}
