package com.blueyonder.platform.u202220783.scm.interfaces.rest.transform;

import com.blueyonder.platform.u202220783.scm.domain.model.aggregates.InventoryItem;
import com.blueyonder.platform.u202220783.scm.interfaces.rest.resources.InventoryItemResource;

/**
 * Assembler to convert a InventoryItem entity to a InventoryItemResource.
 */
public class InventoryItemResourceFromEntityAssembler {
    /**
     * Converts a InventoryItem entity to a InventoryItemResource.
     * @param entity The {@link InventoryItem} entity to convert.
     * @return The {@link InventoryItemResource} resource.
     */
    public static InventoryItemResource toResourceFromEntity(InventoryItem entity) {
        return new InventoryItemResource(
                entity.getId(),
                entity.getSkuIdentifier().value(),
                entity.getStatus().getStringName(),
                entity.getMinimumQuantity(),
                entity.getAvailableQuantity(),
                entity.getReservedQuantity(),
                entity.getPendingSupplyQuantity()
        );
    }
}


