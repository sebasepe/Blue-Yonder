package com.blueyonder.platform.u202220783.scm.domain.model.commands;

public record OrderUnitsFromItemBySkuIdentifierCommand(
        String skuIdentifier,
        Double requestedQuantity
) {
}
