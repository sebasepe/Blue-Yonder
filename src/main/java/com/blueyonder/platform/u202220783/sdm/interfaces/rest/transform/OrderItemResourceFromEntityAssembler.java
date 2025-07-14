package com.blueyonder.platform.u202220783.sdm.interfaces.rest.transform;

import com.blueyonder.platform.u202220783.sdm.domain.model.aggregates.OrderItem;
import com.blueyonder.platform.u202220783.sdm.interfaces.rest.resources.OrderItemResource;

/**
 * Assembler to convert a OrderItem entity to a OrderItemResource.
 */
public class OrderItemResourceFromEntityAssembler {
    /**
     * Converts a OrderItem entity to a OrderItemResource.
     * @param entity The {@link OrderItem} entity to convert.
     * @return The {@link OrderItemResource} resource.
     */
    public static OrderItemResource toResourceFromEntity(OrderItem entity) {
        return new OrderItemResource(
                entity.getId(),
                entity.getOrderId(),
                entity.getSkuIdentifier().value(),
                entity.getStatus().getStringName(),
                entity.getRequestedQuantity(),
                entity.getOrderedAt()
        );
    }
}


