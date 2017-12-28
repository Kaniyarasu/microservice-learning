package com.learning.recruiter.action.offer;

import com.learning.recruiter.api.Offer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static junit.framework.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CreateOfferActionTest {

    @Autowired
    private CreateOfferAction createOfferAction;

    @Test
    public void testCreateOffer() {
        Offer offer = new Offer(null, "Create Test offer", new Date(), 0);
        offer = createOfferAction.invoke(offer);
        assertNotNull(offer.getId());
    }
}