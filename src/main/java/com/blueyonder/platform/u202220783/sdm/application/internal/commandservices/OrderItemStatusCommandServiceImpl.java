package com.blueyonder.platform.u202220783.sdm.application.internal.commandservices;

import com.blueyonder.platform.u202220783.sdm.domain.model.commands.SeedOrderItemStatusesCommand;
import com.blueyonder.platform.u202220783.sdm.domain.model.entities.OrderItemStatus;
import com.blueyonder.platform.u202220783.sdm.domain.model.valueobjects.OrderItemStatuses;
import com.blueyonder.platform.u202220783.sdm.domain.services.OrderItemStatusCommandService;
import com.blueyonder.platform.u202220783.sdm.infrastructure.persistence.jpa.repositories.OrderItemStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Implementation of {@link OrderItemStatusCommandService} to handle {@link SeedOrderItemStatusesCommand}
 */
@Service
public class OrderItemStatusCommandServiceImpl implements OrderItemStatusCommandService {

    private final OrderItemStatusRepository orderItemStatusRepository;

    public OrderItemStatusCommandServiceImpl(OrderItemStatusRepository orderItemStatusRepository) {
        this.orderItemStatusRepository = orderItemStatusRepository;
    }

    /**
     * This method will handle the {@link SeedOrderItemStatusesCommand} and will create the orderItemStatuses if not exists
     * @param command {@link SeedOrderItemStatusesCommand}
     * @see SeedOrderItemStatusesCommand
     */
    @Override
    public void handle(SeedOrderItemStatusesCommand command) {
        Arrays.stream(OrderItemStatuses.values()).forEach(orderItemStatus -> {
            if(!orderItemStatusRepository.existsByName(orderItemStatus)) {
                orderItemStatusRepository.save(new OrderItemStatus(OrderItemStatuses.valueOf(orderItemStatus.name())));
            }
        } );
    }
}


