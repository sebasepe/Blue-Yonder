package com.blueyonder.platform.u202220783.sdm.domain.services;

import com.blueyonder.platform.u202220783.sdm.domain.model.aggregates.OrderItem;
import com.blueyonder.platform.u202220783.sdm.domain.model.commands.CreateOrderItemCommand;

import java.util.Optional;

/**
 * OrderItem Command Service
 */
public interface OrderItemCommandService {
    /**
     * Handle Create OrderItem Command
     *
     * @param command The {@link CreateOrderItemCommand} Command
     * @return A {@link OrderItem} instance if the command is valid, otherwise empty
     * @throws IllegalArgumentException if the email address already exists
     */
    Optional<OrderItem> handle(CreateOrderItemCommand command);
}


