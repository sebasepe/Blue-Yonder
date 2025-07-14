package com.blueyonder.platform.u202220783.scm.interfaces.rest.transform;

import com.blueyonder.platform.u202220783.scm.domain.model.commands.CreateInventoryItemCommand;
import com.blueyonder.platform.u202220783.scm.interfaces.rest.resources.CreateInventoryItemResource;

/**
 * Assembler to convert a CreateInventoryItemResource to a CreateInventoryItemCommand.
 */
public class CreateInventoryItemCommandFromResourceAssembler {
    /**
     * Converts a CreateInventoryItemResource to a CreateInventoryItemCommand.
     * @param resource The {@link CreateInventoryItemResource} resource to convert.
     * @return The {@link CreateInventoryItemCommand} command.
     */
    public static CreateInventoryItemCommand toCommandFromResource(CreateInventoryItemResource resource) {
        return new CreateInventoryItemCommand(
                resource.skuIdentifier(),
                resource.minimumQuantity(),
                resource.availableQuantity()
        );
    }
}


