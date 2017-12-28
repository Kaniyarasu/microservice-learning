package com.learning.recruiter.action.application;

import com.learning.recruiter.api.Application;
import com.learning.recruiter.api.ApplicationStatus;
import com.learning.recruiter.exception.InvalidDataException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CreateApplicationActionTest {

    @Autowired
    private CreateApplicationAction createApplicationAction;

    @Test
    public void testCreateApplication() {
        Application application = createApplicationAction.invoke(
                new Application(
                        null,
                        "test@test.com",
                        "Resume",
                        ApplicationStatus.APPLIED,
                        "CreateA-OfferId-1"
                ));

        assertNotNull(application.getId());
    }

    @Test(expected = InvalidDataException.class)
    public void testCreateDuplicateApplication() {
        createApplicationAction.invoke(
                new Application(
                        null,
                        "test@test.com",
                        "Resume",
                        ApplicationStatus.APPLIED,
                        "CreateA-OfferId-2"
                ));

        createApplicationAction.invoke(
                new Application(
                        null,
                        "test@test.com",
                        "Resume",
                        ApplicationStatus.APPLIED,
                        "CreateA-OfferId-2"
                ));
    }
}