package com.learning.recruiter.action.offer;

import com.learning.recruiter.action.application.CreateApplicationAction;
import com.learning.recruiter.api.Application;
import com.learning.recruiter.api.ApplicationStatus;
import com.learning.recruiter.api.Offer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GetOfferActionTest {

    @Autowired
    private GetOfferAction getOfferAction;

    @Autowired
    private CreateOfferAction createOfferAction;

    @Autowired
    private CreateApplicationAction createApplicationAction;

    @Test
    public void testGetOffer() {
        Offer offer = createOfferAction.invoke(prepareOffer(null));

        Offer offerFromAction = getOfferAction.invoke(offer.getId());
        assertNotNull(offerFromAction);
        assertEquals(offerFromAction.getId(), offer.getId());
    }

    @Test
    public void testGetOfferApplications() {
        Offer offer = createOfferAction.invoke(prepareOffer("Get Test offer 1"));

        createApplicationAction.invoke(
                new Application(
                        null,
                        "test@test.com",
                        "Resume",
                        ApplicationStatus.APPLIED,
                        offer.getId()
                ));

        createApplicationAction.invoke(
                new Application(
                        null,
                        "test1@test.com",
                        "Resume",
                        ApplicationStatus.APPLIED,
                        offer.getId()
                ));

        Offer offerFromAction = getOfferAction.invoke(offer.getId());
        assertEquals(offerFromAction.getNumberOfApplicants(), new Integer(2));
    }

    private Offer prepareOffer(String title) {
        return new Offer(null, null != title ? title : "Get Test offer", new Date(), 0);
    }
}