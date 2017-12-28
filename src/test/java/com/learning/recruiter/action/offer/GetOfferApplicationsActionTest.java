package com.learning.recruiter.action.offer;

import com.learning.recruiter.action.application.CreateApplicationAction;
import com.learning.recruiter.api.Application;
import com.learning.recruiter.api.ApplicationStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GetOfferApplicationsActionTest {
    @Autowired
    private CreateApplicationAction createApplicationAction;

    @Autowired
    private GetOfferApplicationsAction getOfferApplicationsAction;

    @Test
    public void testGetOfferApplications() {
        createApplicationAction.invoke(
                new Application(
                        null,
                        "test@test.com",
                        "Resume",
                        ApplicationStatus.APPLIED,
                        "GetOA-OfferId"
                ));

        createApplicationAction.invoke(
                new Application(
                        null,
                        "test1@test.com",
                        "Resume",
                        ApplicationStatus.APPLIED,
                        "GetOA-OfferId"
                ));

        assertEquals(getOfferApplicationsAction.invoke("GetOA-OfferId").size(), 2);
    }
}