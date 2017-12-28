package com.learning.recruiter.repository;

import com.learning.recruiter.api.ApplicationStatus;
import com.learning.recruiter.entity.ApplicationEntity;
import com.learning.recruiter.exception.DataNotExistsException;
import com.learning.recruiter.exception.InvalidDataException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationRepositoryTest {

    public static final String TEST_OFFER_ID = "testOfferId";

    @InjectMocks
    ApplicationRepository applicationRepository;

    @Test
    public void testCreateApplication() {
        ApplicationEntity applicationEntity = prepareApplicationEntity(null);
        applicationEntity = applicationRepository.createApplication(applicationEntity);
        assertNotNull(applicationEntity.getId());
    }

    @Test(expected = InvalidDataException.class)
    public void testDuplicateApplications() {
        ApplicationEntity applicationEntity = prepareApplicationEntity(null);
        applicationRepository.createApplication(applicationEntity);

        applicationEntity = prepareApplicationEntity(null);
        applicationRepository.createApplication(applicationEntity);

        applicationEntity = prepareApplicationEntity("Test@Test.com");
        applicationRepository.createApplication(applicationEntity);
    }

    @Test
    public void testFindOneApplication() {
        ApplicationEntity applicationEntity = prepareApplicationEntity(null);
        applicationEntity = applicationRepository.createApplication(applicationEntity);

        ApplicationEntity newEntity = applicationRepository.findOne(applicationEntity.getId());

        assertNotNull(applicationEntity);
        assertEquals(applicationEntity.getId(), newEntity.getId());
    }

    @Test(expected = DataNotExistsException.class)
    public void testApplicationNotFound() {
        ApplicationEntity applicationEntity = prepareApplicationEntity(null);
        applicationRepository.createApplication(applicationEntity);

        applicationRepository.findOne("random");
    }

    @Test
    public void testUpdateStatus() {
        ApplicationEntity applicationEntity = prepareApplicationEntity(null);
        applicationEntity = applicationRepository.createApplication(applicationEntity);
        assertEquals(ApplicationStatus.APPLIED, applicationEntity.getStatus());

        applicationEntity = applicationRepository.updateStatus(applicationEntity.getId(), ApplicationStatus.INVITED);
        assertEquals(ApplicationStatus.INVITED, applicationEntity.getStatus());
    }

    @Test
    public void findOfferApplications() {
        ApplicationEntity firstApplication = applicationRepository.createApplication(prepareApplicationEntity(null));
        ApplicationEntity secondApplication = applicationRepository.createApplication(prepareApplicationEntity("test1@test.com"));

        List<ApplicationEntity> offerApplications = applicationRepository.findOfferApplications(TEST_OFFER_ID);
        assertNotNull(offerApplications);
        assertEquals(2, offerApplications.size());

        offerApplications
                .forEach(applicationEntity ->
                                assertTrue(applicationEntity.getId().equals(firstApplication.getId())
                                        || applicationEntity.getId().equals(secondApplication.getId()))
                );
    }

    private ApplicationEntity prepareApplicationEntity(String candidateEmail) {
        return new ApplicationEntity(
                null != candidateEmail ? candidateEmail : "test@test.com",
                "Resume",
                ApplicationStatus.APPLIED,
                TEST_OFFER_ID
        );
    }
}