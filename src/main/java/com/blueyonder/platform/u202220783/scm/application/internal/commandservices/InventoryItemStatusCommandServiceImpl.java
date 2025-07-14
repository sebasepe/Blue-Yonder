package com.blueyonder.platform.u202220783.scm.application.internal.commandservices;

import com.blueyonder.platform.u202220783.scm.domain.model.commands.SeedInventoryItemStatusesCommand;
import com.blueyonder.platform.u202220783.scm.domain.model.entities.InventoryItemStatus;
import com.blueyonder.platform.u202220783.scm.domain.model.valueobjects.InventoryItemStatuses;
import com.blueyonder.platform.u202220783.scm.domain.services.InventoryItemStatusCommandService;
import com.blueyonder.platform.u202220783.scm.infrastructure.persistence.jpa.repositories.InventoryItemStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Implementation of {@link InventoryItemStatusCommandService} to handle {@link SeedInventoryItemStatusesCommand}
 */
@Service
public class InventoryItemStatusCommandServiceImpl implements InventoryItemStatusCommandService {

    private final InventoryItemStatusRepository inventoryItemStatusRepository;

    public InventoryItemStatusCommandServiceImpl(InventoryItemStatusRepository inventoryItemStatusRepository) {
        this.inventoryItemStatusRepository = inventoryItemStatusRepository;
    }

    /**
     * This method will handle the {@link SeedInventoryItemStatusesCommand} and will create the inventoryItemStatuses if not exists
     * @param command {@link SeedInventoryItemStatusesCommand}
     * @see SeedInventoryItemStatusesCommand
     */
    @Override
    public void handle(SeedInventoryItemStatusesCommand command) {
        Arrays.stream(InventoryItemStatuses.values()).forEach(inventoryItemStatus -> {
            if(!inventoryItemStatusRepository.existsByName(inventoryItemStatus)) {
                inventoryItemStatusRepository.save(new InventoryItemStatus(InventoryItemStatuses.valueOf(inventoryItemStatus.name())));
            }
        } );
    }
}


