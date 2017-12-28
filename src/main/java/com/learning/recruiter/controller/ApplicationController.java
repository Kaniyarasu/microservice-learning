package com.learning.recruiter.controller;

import com.learning.recruiter.action.application.CreateApplicationAction;
import com.learning.recruiter.action.application.GetApplicationAction;
import com.learning.recruiter.action.application.UpdateApplicationStatusAction;
import com.learning.recruiter.api.APIResponse;
import com.learning.recruiter.api.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for Applications
 */
@RestController
@RequestMapping(path = "/api/v1/application")
public class ApplicationController
{

    @Autowired
    private CreateApplicationAction createApplicationAction;

    @Autowired
    private GetApplicationAction getApplicationAction;

    @Autowired
    private UpdateApplicationStatusAction updateApplicationStatusAction;

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public APIResponse<Application> createApplication(@RequestBody Application application){
        return new APIResponse<>(createApplicationAction.invoke(application));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path = "/{applicationId}", method = RequestMethod.GET, produces = "application/json")
    public APIResponse<Application> getApplication(@PathVariable("applicationId") String applicationId){
        return new APIResponse<>(getApplicationAction.invoke(applicationId));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public APIResponse<Application> updateApplicationStatus(@RequestBody Application application){
        return new APIResponse<>(updateApplicationStatusAction.invoke(application.getId(), application.getStatus()));
    }
}
