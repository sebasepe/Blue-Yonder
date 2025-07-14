package com.blueyonder.platform.u202220783.sdm.interfaces.rest.resources;

import java.util.Date;

public record OrderItemResource(
        Long id,
        Long orderId,
        String skuIdentifier,
        String status,
        Double requestedQuantity,
        Date orderedAt
) {
}
