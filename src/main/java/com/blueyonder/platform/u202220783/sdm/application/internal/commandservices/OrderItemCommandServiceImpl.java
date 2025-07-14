package com.blueyonder.platform.u202220783.sdm.application.internal.commandservices;

import com.blueyonder.platform.u202220783.shared.domain.model.valueobjects.SkuIdentifier;
import com.blueyonder.platform.u202220783.sdm.application.internal.outboundservices.acl.ExternalSCMService;
import com.blueyonder.platform.u202220783.sdm.domain.model.aggregates.OrderItem;
import com.blueyonder.platform.u202220783.sdm.domain.model.commands.CreateOrderItemCommand;
import com.blueyonder.platform.u202220783.sdm.domain.model.entities.OrderItemStatus;
import com.blueyonder.platform.u202220783.sdm.domain.model.valueobjects.OrderItemStatuses;
import com.blueyonder.platform.u202220783.sdm.domain.services.OrderItemCommandService;
import com.blueyonder.platform.u202220783.sdm.infrastructure.persistence.jpa.repositories.OrderItemRepository;
import com.blueyonder.platform.u202220783.sdm.infrastructure.persistence.jpa.repositories.OrderItemStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * OrderItem Command Service Implementation
 */
@Service
public class OrderItemCommandServiceImpl implements OrderItemCommandService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemStatusRepository orderItemStatusRepository;
    private final ExternalSCMService externalSCMService;

    /**
     * Constructor
     *
     * @param orderItemRepository The {@link OrderItemRepository} instance
     */
    public OrderItemCommandServiceImpl(OrderItemRepository orderItemRepository, OrderItemStatusRepository orderItemStatusRepository, ExternalSCMService externalSCMService) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemStatusRepository = orderItemStatusRepository;
        this.externalSCMService = externalSCMService;
    }

    // inherited javadoc
    @Override
    public Optional<OrderItem> handle(CreateOrderItemCommand command) {
        if (orderItemRepository.existsByOrderIdAndSkuIdentifier(
                command.orderId(), new SkuIdentifier(command.skuIdentifier())
        )) throw new IllegalArgumentException("There is an order item for the order with id: " + command.orderId() + " for the inventory item with sku identifier: " + command.skuIdentifier());

        if (!externalSCMService.existsBySkuIdentifier(
                command.skuIdentifier()
        )) throw new IllegalArgumentException("The provided sku identifier: " + command.skuIdentifier() + " does not belong to any known inventory item.");

        OrderItem orderItem = new OrderItem(command);

        // No olvidar que la facha va a retornar true o false
        // La regla de negocio dice que si es true el estado es READY
        // Caso contrario, debe ser WAITING
        if(externalSCMService.inventoryHasEnoughUnitsForOrder(command.skuIdentifier(), command.requestedQuantity())) {
            OrderItemStatus readyStatus = orderItemStatusRepository.findByName(OrderItemStatuses.READY_FOR_DISPATCH)
                    .orElseThrow(()-> new RuntimeException("Error while trying to get READY_FOR_DISPATCH status from database."));
            orderItem.setStatus(readyStatus);
        }
        else {
            OrderItemStatus waitingStatus = orderItemStatusRepository.findByName(OrderItemStatuses.WAITING_FOR_INVENTORY)
                    .orElseThrow(()-> new RuntimeException("Error while trying to get READY_FOR_DISPATCH status from database."));
            orderItem.setStatus(waitingStatus);
        }

        orderItemRepository.save(orderItem);
        return Optional.of(orderItem);
    }
}


