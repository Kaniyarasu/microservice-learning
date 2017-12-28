package com.learning.recruiter.helper;

import com.learning.recruiter.api.EventType;
import lombok.extern.slf4j.Slf4j;

/**
 *  EventPublisher provides support to publish the different event and notifies the specific event handlers
 *      # Right supports only logging of the received event
 */
@Slf4j
public class EventPublisher {
    public static void publish(String objectId, EventType eventType) {
        log.info("New Event: {}, Object Id: {}", eventType, objectId);
    }
}
