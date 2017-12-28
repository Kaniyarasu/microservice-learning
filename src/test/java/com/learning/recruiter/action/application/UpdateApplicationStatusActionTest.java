package com.learning.recruiter.action.application;

import com.learning.recruiter.api.Application;
import com.learning.recruiter.api.ApplicationStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UpdateApplicationStatusActionTest {

    @Autowired
    private CreateApplicationAction createApplicationAction;

    @Autowired
    private UpdateApplicationStatusAction updateApplicationStatusAction;

    @Test
    public void testCreateApplication() {
        Application application = createApplicationAction.invoke(
                new Application(
                        null,
                        "test@test.com",
                        "Resume",
                        ApplicationStatus.APPLIED,
                        "UpdateAS-OfferId"
                ));

        assertEquals(application.getStatus(), ApplicationStatus.APPLIED);
        Application applicationAfterUpdate = updateApplicationStatusAction.invoke(application.getId(), ApplicationStatus.INVITED);
        assertEquals(applicationAfterUpdate.getStatus(), ApplicationStatus.INVITED);
    }
}