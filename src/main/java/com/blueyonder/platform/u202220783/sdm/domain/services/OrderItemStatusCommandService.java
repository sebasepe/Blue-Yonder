package com.blueyonder.platform.u202220783.sdm.domain.services;

import com.blueyonder.platform.u202220783.sdm.domain.model.commands.SeedOrderItemStatusesCommand;

/**
 * OrderItemStatus command service
 * <p>
 *     This interface represents the service to handle orderItemStatus commands.
 * </p>
 */
public interface OrderItemStatusCommandService {
    /**
     * Handle seed orderItemStatuss command
     * @param command the {@link SeedOrderItemStatusesCommand} command
     *
     */
    void handle(SeedOrderItemStatusesCommand command);
}


