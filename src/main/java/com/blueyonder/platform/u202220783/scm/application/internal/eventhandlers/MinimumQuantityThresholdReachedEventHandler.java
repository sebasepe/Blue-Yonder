package com.blueyonder.platform.u202220783.scm.application.internal.eventhandlers;

import com.blueyonder.platform.u202220783.scm.domain.model.commands.SetInventoryItemStatusToUnderMinimumCommand;
import com.blueyonder.platform.u202220783.scm.domain.model.events.MinimumQuantityThresholdReachedEvent;
import com.blueyonder.platform.u202220783.scm.domain.services.InventoryItemCommandService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * Event handler for the MinimumQuantityThresholdReachedEvent.
 */
@Service
public class MinimumQuantityThresholdReachedEventHandler {
    private final InventoryItemCommandService inventoryItemCommandService;

    /**
     * Constructor.
     *
     * @param inventoryItemCommandService the student command service
     * @see InventoryItemCommandService
     */
    public MinimumQuantityThresholdReachedEventHandler(InventoryItemCommandService inventoryItemCommandService) {
        this.inventoryItemCommandService = inventoryItemCommandService;
    }

    /**
     * Handles the MinimumQuantityThresholdReachedEvent.
     * <p>
     *     Updates the student metrics when a tutorial is completed. The student metrics are updated by the
     *     {@link SetInventoryItemStatusToUnderMinimumCommand}.
     * </p>
     *
     * @param event The {@link MinimumQuantityThresholdReachedEvent} event
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void on(MinimumQuantityThresholdReachedEvent event) {
        System.out.println(
                "SCM: A supply order is needed for the product with SKU: " + event.getSkuIdentifier() + " with at least: " + event.getRequestedSupplyQuantity() +  " units"
        );

        inventoryItemCommandService.handle(
                new SetInventoryItemStatusToUnderMinimumCommand(event.getSkuIdentifier())
        );
    }
}

