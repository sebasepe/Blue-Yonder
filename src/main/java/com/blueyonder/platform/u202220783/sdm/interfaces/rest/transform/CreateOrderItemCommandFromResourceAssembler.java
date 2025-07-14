package com.blueyonder.platform.u202220783.sdm.interfaces.rest.transform;

import com.blueyonder.platform.u202220783.sdm.domain.model.commands.CreateOrderItemCommand;
import com.blueyonder.platform.u202220783.sdm.interfaces.rest.resources.CreateOrderItemResource;

/**
 * Assembler to convert a CreateOrderItemResource to a CreateOrderItemCommand.
 */
public class CreateOrderItemCommandFromResourceAssembler {
    /**
     * Converts a CreateOrderItemResource to a CreateOrderItemCommand.
     * @param resource The {@link CreateOrderItemResource} resource to convert.
     * @return The {@link CreateOrderItemCommand} command.
     */
    public static CreateOrderItemCommand toCommandFromResource(CreateOrderItemResource resource, Long orderId) {
        return new CreateOrderItemCommand(
                resource.skuIdentifier(),
                orderId,
                resource.requestedQuantity(),
                resource.orderedAt()
        );
    }
}


