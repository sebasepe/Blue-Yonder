package com.blueyonder.platform.u202220783.sdm.domain.model.commands;

import java.util.Date;

public record CreateOrderItemCommand(
        String skuIdentifier,
        Long orderId,
        Double requestedQuantity,
        Date orderedAt) {
}
