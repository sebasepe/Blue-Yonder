package com.blueyonder.platform.u202220783.shared.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import java.util.UUID;

/**
 * Value object representing the student record id.
 * @summary
 * This value object is used to represent the SKU identifier of a inventory item. It is used to track the amount of available units for the given inventory item.
 * @see IllegalArgumentException
 * @since 1.0
 */
@Embeddable
public record SkuIdentifier(String value) {
    /**
     * Default constructor.
     * @summary
     * This constructor is used to create a new instance of the SkuIdentifier value object. It generates a new UUID and sets it as the student record id.
     * @since 1.0
     */
    public SkuIdentifier() {
        this(UUID.randomUUID().toString());
    }

    public SkuIdentifier {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Sku identifier cannot be null or empty.");
        }

        // Muy probablemente haya que tener este pedazo de código a la mano durante el final
        if (!value.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$")) {
            throw new IllegalArgumentException("Sku identifier must meet the UUID v4 format.");
        }
    }
}


