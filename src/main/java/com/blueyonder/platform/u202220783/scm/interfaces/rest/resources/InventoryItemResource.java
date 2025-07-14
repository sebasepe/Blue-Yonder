package com.blueyonder.platform.u202220783.scm.interfaces.rest.resources;

/**
 * Resource to represent an inventoryItem.
 */
public record InventoryItemResource(
        Long id,
        String skuIdentifier,
        String status,
        Double minimumQuantity,
        Double availableQuantity,
        Double reservedQuantity,
        Double pendingSupplyQuantity
) {

}
