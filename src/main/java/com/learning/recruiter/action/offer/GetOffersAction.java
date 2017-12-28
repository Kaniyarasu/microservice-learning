package com.learning.recruiter.action.offer;

import com.learning.recruiter.api.Offer;
import com.learning.recruiter.mapper.OfferMapper;
import com.learning.recruiter.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Action for returning all the offers, pagination not supported
 */
@Component
public class GetOffersAction
{

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private OfferMapper offerMapper;

    @Autowired
    private GetOfferApplicationsAction getOfferApplicationsAction;

    public List<Offer> invoke() {
        return offerRepository.findAll()
                .stream()
                .map(offerEntity -> {
                    Offer offer = offerMapper.mapFromEntity(offerEntity);
                    offer.setNumberOfApplicants(
                            getOfferApplicationsAction.invoke(offer.getId()).size());
                    return offer;
                })
                .collect(Collectors.toList());
    }
}
