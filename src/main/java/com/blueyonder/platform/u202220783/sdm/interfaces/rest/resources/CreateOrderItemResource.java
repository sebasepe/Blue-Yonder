package com.blueyonder.platform.u202220783.sdm.interfaces.rest.resources;

import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

/**
 * Resource for creating a profile.
 */
public record CreateOrderItemResource(
        String skuIdentifier,
        Double requestedQuantity,
        @PastOrPresent Date orderedAt
) {
    /**
     * Validates the resource.
     *
     * @throws IllegalArgumentException if the resource is invalid.
     */
    public CreateOrderItemResource {
        if (skuIdentifier == null || skuIdentifier.isBlank()) throw new IllegalArgumentException("Sku identifier is required");
        if (requestedQuantity == null) throw new IllegalArgumentException("Requested quantity is required");
        if (orderedAt == null) throw new IllegalArgumentException("Order's date is required");
    }
}


