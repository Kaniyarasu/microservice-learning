package com.learning.recruiter.repository;

import com.learning.recruiter.entity.OfferEntity;
import com.learning.recruiter.exception.DataNotExistsException;
import com.learning.recruiter.exception.InvalidDataException;
import com.learning.recruiter.util.Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OfferRepositoryTest {

    @InjectMocks
    OfferRepository offerRepository;

    @Test
    public void testCreateOffer() {
        OfferEntity offerEntity = prepareOffer(null);
        offerEntity = offerRepository.createOffer(offerEntity);
        assertNotNull(offerEntity.getId());
    }

    @Test(expected = InvalidDataException.class)
    public void testDuplicateOffer() {
        OfferEntity offerEntity = prepareOffer(null);
        offerRepository.createOffer(offerEntity);

        //Test for duplicates
        offerEntity = prepareOffer(null);
        offerRepository.createOffer(offerEntity);

        //Test with case duplicates
        offerEntity = prepareOffer("test title");
        offerRepository.createOffer(offerEntity);
    }

    @Test
    public void testGetOffer() {
        OfferEntity offerEntity = prepareOffer(null);
        offerEntity = offerRepository.createOffer(offerEntity);

        OfferEntity newEntity = offerRepository.findOne(offerEntity.getId());
        assertNotNull(newEntity);
        assertEquals(offerEntity.getId(), newEntity.getId());
    }

    @Test(expected = DataNotExistsException.class)
    public void testGetNoOffer() {
        OfferEntity offerEntity = prepareOffer(null);
        offerRepository.createOffer(offerEntity);
        offerRepository.findOne(Util.generateId());
    }

    private OfferEntity prepareOffer(String title) {
        return new OfferEntity(null != title ? title : "Test title", new Date());
    }
}