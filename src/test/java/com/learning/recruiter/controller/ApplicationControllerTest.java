package com.learning.recruiter.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.learning.recruiter.action.application.CreateApplicationAction;
import com.learning.recruiter.api.APIResponse;
import com.learning.recruiter.api.Application;
import com.learning.recruiter.api.ApplicationStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static junit.framework.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ApplicationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CreateApplicationAction createApplicationAction;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateOffer() throws Exception {
        Application application = new Application(
                null,
                "test@test.com",
                "Resume",
                ApplicationStatus.APPLIED,
                "CreateA-OfferId-1"
        );

        MvcResult mvcResult = this.mockMvc.perform(post("/api/v1/application")
                .contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(application)))
                .andExpect(status().isCreated())
                .andReturn();

        APIResponse<Application> offerAPIResponse = new Gson().fromJson(
                mvcResult.getResponse().getContentAsString(),
                new TypeToken<APIResponse<Application>>() {}.getType());

        assertNotNull(offerAPIResponse.getPayload().getId());
    }

    @Test
    public void getGetOffer() throws Exception {
        Application application = createApplicationAction.invoke(new Application(
                null,
                "test@test.com",
                "Resume",
                ApplicationStatus.APPLIED,
                "CreateA-OfferId-2"
        ));

        this.mockMvc.perform(get("/api/v1/application/" + application.getId()))
                .andExpect(status().isOk());
    }
}