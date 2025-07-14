package com.blueyonder.platform.u202220783.scm.domain.services;

import com.blueyonder.platform.u202220783.scm.domain.model.aggregates.InventoryItem;
import com.blueyonder.platform.u202220783.scm.domain.model.commands.CreateInventoryItemCommand;
import com.blueyonder.platform.u202220783.scm.domain.model.commands.OrderUnitsFromItemBySkuIdentifierCommand;
import com.blueyonder.platform.u202220783.scm.domain.model.commands.SetInventoryItemStatusToUnderMinimumCommand;

import java.util.Optional;

/**
 * InventoryItem Command Service
 */
public interface InventoryItemCommandService {
    /**
     * Handle Create InventoryItem Command
     *
     * @param command The {@link CreateInventoryItemCommand} Command
     * @return A {@link InventoryItem} instance if the command is valid, otherwise empty
     * @throws IllegalArgumentException if the email address already exists
     */
    Optional<InventoryItem> handle(CreateInventoryItemCommand command);

    /**
     * Order Units From Item By Sku Identifier Command
     *
     * @param command The {@link CreateInventoryItemCommand} Command
     * @return true if the order was able to be managed with available quantity of the given inventory item. False otherwise
     */
    boolean handle(OrderUnitsFromItemBySkuIdentifierCommand command);

    void handle(SetInventoryItemStatusToUnderMinimumCommand command);
}


