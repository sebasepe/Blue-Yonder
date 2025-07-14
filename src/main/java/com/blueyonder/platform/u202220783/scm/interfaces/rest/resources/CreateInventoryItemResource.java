package com.blueyonder.platform.u202220783.scm.interfaces.rest.resources;

/**
 * Resource for creating an inventoryItem.
 */
public record CreateInventoryItemResource(
        String skuIdentifier,
        Double minimumQuantity,
        Double availableQuantity
) {
    /**
     * Validates the resource.
     *
     * @throws IllegalArgumentException if the resource is invalid.
     */
    public CreateInventoryItemResource {
        if (skuIdentifier == null || skuIdentifier.isBlank()) throw new IllegalArgumentException("First name is required");
        if (minimumQuantity == null) throw new IllegalArgumentException("Minimum quantity is required");
        if (availableQuantity == null) throw new IllegalArgumentException("Available quantity is required");
    }
}


