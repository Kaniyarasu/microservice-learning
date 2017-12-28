package com.learning.recruiter.action.offer;

import com.learning.recruiter.api.Offer;
import com.learning.recruiter.repository.OfferRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GetOffersActionTest
{

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private GetOffersAction getOffersAction;

    @Autowired
    private CreateOfferAction createOfferAction;

    @Test
    public void testGetAllOffers() {
        //Should have been used mocking but because time constraint using Hack for the test
        offerRepository.deleteAll();

        createOfferAction.invoke(prepareOffer(null));
        createOfferAction.invoke(prepareOffer("Get All Test offer 2"));

        assertEquals(getOffersAction.invoke().size(), 2);
    }

    private Offer prepareOffer(String title) {
        return new Offer(null, null != title ? title : "Get All Test offer", new Date(), 0);
    }
}