package com.blueyonder.platform.u202220783.scm.application.internal.eventhandlers;

import com.blueyonder.platform.u202220783.scm.domain.model.commands.SeedInventoryItemStatusesCommand;
import com.blueyonder.platform.u202220783.scm.domain.services.InventoryItemStatusCommandService;
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
@Service("SCMAppReady")
public class ApplicationReadyEventHandler {
    private final InventoryItemStatusCommandService inventoryItemStatusCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);

    public ApplicationReadyEventHandler(InventoryItemStatusCommandService inventoryItemStatusCommandService) {
        this.inventoryItemStatusCommandService = inventoryItemStatusCommandService;
    }

    /**
     * Handle the ApplicationReadyEvent
     * This method is used to seed the inventoryItemStatuses
     * @param event the ApplicationReadyEvent the event to handle
     */
    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify if inventoryItemStatuses seeding is needed for {} at {}", applicationName, currentTimestamp());
        var seedInventoryItemStatusesCommand = new SeedInventoryItemStatusesCommand();
        inventoryItemStatusCommandService.handle(seedInventoryItemStatusesCommand);
        LOGGER.info("InventoryItemStatuses seeding verification finished for {} at {}", applicationName, currentTimestamp());
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}


