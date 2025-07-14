package com.blueyonder.platform.u202220783.scm.application.internal.commandservices;

import com.blueyonder.platform.u202220783.scm.domain.model.aggregates.InventoryItem;
import com.blueyonder.platform.u202220783.scm.domain.model.commands.CreateInventoryItemCommand;
import com.blueyonder.platform.u202220783.scm.domain.model.commands.OrderUnitsFromItemBySkuIdentifierCommand;
import com.blueyonder.platform.u202220783.scm.domain.model.commands.SetInventoryItemStatusToUnderMinimumCommand;
import com.blueyonder.platform.u202220783.scm.domain.model.entities.InventoryItemStatus;
import com.blueyonder.platform.u202220783.scm.domain.model.valueobjects.InventoryItemStatuses;
import com.blueyonder.platform.u202220783.shared.domain.model.valueobjects.SkuIdentifier;
import com.blueyonder.platform.u202220783.scm.domain.services.InventoryItemCommandService;
import com.blueyonder.platform.u202220783.scm.infrastructure.persistence.jpa.repositories.InventoryItemRepository;
import com.blueyonder.platform.u202220783.scm.infrastructure.persistence.jpa.repositories.InventoryItemStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * InventoryItem Command Service Implementation
 */
@Service
public class InventoryItemCommandServiceImpl implements InventoryItemCommandService {
    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryItemStatusRepository inventoryItemStatusRepository;

    /**
     * Constructor
     *
     * @param inventoryItemRepository The {@link InventoryItemRepository} instance
     */
    public InventoryItemCommandServiceImpl(InventoryItemRepository inventoryItemRepository, InventoryItemStatusRepository inventoryItemStatusRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
        this.inventoryItemStatusRepository = inventoryItemStatusRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<InventoryItem> handle(CreateInventoryItemCommand command) {
        if(inventoryItemRepository.existsBySkuIdentifier(
                new SkuIdentifier(command.skuIdentifier())
        )) throw new IllegalArgumentException("An inventory item with the sku identifier: " +  command.skuIdentifier() + " exists already.");

        InventoryItemStatus status = inventoryItemStatusRepository.findByName(
                InventoryItemStatuses.WITH_STOCK
        ).orElseThrow(() -> new RuntimeException("Error while trying to get WITH_STOCK status from database."));

        InventoryItem inventoryItem = new InventoryItem(command);
        inventoryItem.setStatus(status);

        inventoryItemRepository.save(inventoryItem);
        return Optional.of(inventoryItem);
    }

    @Override
    public boolean handle(OrderUnitsFromItemBySkuIdentifierCommand command) {
        InventoryItem inventoryItem = inventoryItemRepository.findBySkuIdentifier(
                new SkuIdentifier(command.skuIdentifier())
        ).orElseThrow(()-> new IllegalArgumentException("Could not find an inventory item for the provided sku identifier: " + command.skuIdentifier()));

        var response = inventoryItem.isThereEnoughStockForOrder(command);

        inventoryItemRepository.save(inventoryItem);

        return response;
    }

    @Override
    public void handle(SetInventoryItemStatusToUnderMinimumCommand command) {
        InventoryItem inventoryItem = inventoryItemRepository.findBySkuIdentifier(
                new SkuIdentifier(command.skuIdentifier())
        ).orElseThrow(()-> new IllegalArgumentException("Could not find an inventory item for the provided sku identifier: " + command.skuIdentifier()));

        InventoryItemStatus underMinimumStatus = inventoryItemStatusRepository.findByName(InventoryItemStatuses.UNDER_MINIMUM)
                .orElseThrow(() -> new RuntimeException("Error while trying to get UNDER_MINIMUM status from database."));

        inventoryItem.setStatus(underMinimumStatus);

        inventoryItemRepository.save(inventoryItem);
    }
}


