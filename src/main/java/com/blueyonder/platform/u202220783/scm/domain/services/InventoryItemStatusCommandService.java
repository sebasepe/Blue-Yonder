package com.blueyonder.platform.u202220783.scm.domain.services;

import com.blueyonder.platform.u202220783.scm.domain.model.commands.SeedInventoryItemStatusesCommand;

/**
 * InventoryItemStatus command service
 * <p>
 *     This interface represents the service to handle inventoryItemStatus commands.
 * </p>
 */
public interface InventoryItemStatusCommandService {
    /**
     * Handle seed inventoryItemStatuses command
     * @param command the {@link SeedInventoryItemStatusesCommand} command
     *
     */
    void handle(SeedInventoryItemStatusesCommand command);
}


