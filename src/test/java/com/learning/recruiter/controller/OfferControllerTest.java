package com.learning.recruiter.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.learning.recruiter.action.offer.CreateOfferAction;
import com.learning.recruiter.api.APIResponse;
import com.learning.recruiter.api.Offer;
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

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static junit.framework.Assert.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class OfferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CreateOfferAction createOfferAction;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateOffer() throws Exception {
        Offer offer = new Offer(null, "Create Test offer controller", new Date(), 0);
        MvcResult mvcResult = this.mockMvc.perform(post("/api/v1/offer")
                .contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(offer)))
                .andExpect(status().isCreated())
                .andReturn();

        APIResponse<Offer> offerAPIResponse = new Gson().fromJson(
                mvcResult.getResponse().getContentAsString(),
                new TypeToken<APIResponse<Offer>>() {}.getType());

        assertNotNull(offerAPIResponse.getPayload().getId());
    }

    @Test
    public void getGetOffer() throws Exception {
        Offer offer = createOfferAction.invoke(new Offer(null, "Create Test offer controller 1", new Date(), 0));
        this.mockMvc.perform(get("/api/v1/offer/" + offer.getId()))
                .andExpect(status().isOk());
    }
}