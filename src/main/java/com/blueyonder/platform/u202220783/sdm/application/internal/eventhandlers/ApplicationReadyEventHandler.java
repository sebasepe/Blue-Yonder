package com.blueyonder.platform.u202220783.sdm.application.internal.eventhandlers;

import com.blueyonder.platform.u202220783.sdm.domain.model.commands.SeedOrderItemStatusesCommand;
import com.blueyonder.platform.u202220783.sdm.domain.services.OrderItemStatusCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * ApplicationReadyEventHandler class
 * This class is used to handle the ApplicationReadyEvent
 */
@Service
public class ApplicationReadyEventHandler {
    private final OrderItemStatusCommandService orderItemStatusCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);

    public ApplicationReadyEventHandler(OrderItemStatusCommandService orderItemStatusCommandService) {
        this.orderItemStatusCommandService = orderItemStatusCommandService;
    }

    /**
     * Handle the ApplicationReadyEvent
     * This method is used to seed the orderItemStatuses
     * @param event the ApplicationReadyEvent the event to handle
     */
    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify if orderItemStatuses seeding is needed for {} at {}", applicationName, currentTimestamp());
        var seedOrderItemStatusesCommand = new SeedOrderItemStatusesCommand();
        orderItemStatusCommandService.handle(seedOrderItemStatusesCommand);
        LOGGER.info("OrderItemStatuses seeding verification finished for {} at {}", applicationName, currentTimestamp());
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}


