package com.learning.recruiter.controller;

import com.learning.recruiter.action.offer.GetOfferApplicationsAction;
import com.learning.recruiter.action.offer.CreateOfferAction;
import com.learning.recruiter.action.offer.GetOfferAction;
import com.learning.recruiter.action.offer.GetOffersAction;
import com.learning.recruiter.api.APIResponse;
import com.learning.recruiter.api.Application;
import com.learning.recruiter.api.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Offers
 */
@RestController
@RequestMapping(path = "/api/v1/offer")
public class OfferController
{
    @Autowired
    private CreateOfferAction createOfferAction;

    @Autowired
    private GetOfferAction getOfferAction;

    @Autowired
    private GetOffersAction getOffersAction;

    @Autowired
    private GetOfferApplicationsAction getOfferApplicationsAction;

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public APIResponse<Offer> createOffer(@RequestBody Offer offer){
        return new APIResponse<>(createOfferAction.invoke(offer));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{offerId}", method = RequestMethod.GET, produces = "application/json")
    public APIResponse<Offer> getOffer(@PathVariable("offerId") String offerId){
        return new APIResponse<>(getOfferAction.invoke(offerId));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public APIResponse<List<Offer>> getOffers(){
        return new APIResponse<>(getOffersAction.invoke());
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{offerId}/applications", method = RequestMethod.GET, produces = "application/json")
    public APIResponse<List<Application>> getOfferApplications(@PathVariable("offerId") String offerId){
        return new APIResponse<>(getOfferApplicationsAction.invoke(offerId));
    }
}
