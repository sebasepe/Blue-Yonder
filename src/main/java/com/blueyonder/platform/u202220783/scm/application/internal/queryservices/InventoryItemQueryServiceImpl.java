package com.blueyonder.platform.u202220783.scm.application.internal.queryservices;

import com.blueyonder.platform.u202220783.scm.domain.model.queries.ExistsBySkuIdentifierQuery;
import com.blueyonder.platform.u202220783.shared.domain.model.valueobjects.SkuIdentifier;
import com.blueyonder.platform.u202220783.scm.domain.services.InventoryItemQueryService;
import com.blueyonder.platform.u202220783.scm.infrastructure.persistence.jpa.repositories.InventoryItemRepository;
import org.springframework.stereotype.Service;

/**
 * InventoryItem Query Service Implementation
 */
@Service
public class InventoryItemQueryServiceImpl implements InventoryItemQueryService {
    private final InventoryItemRepository inventoryItemRepository;

    /**
     * Constructor
     *
     * @param profileRepository The {@link InventoryItemRepository} instance
     */
    public InventoryItemQueryServiceImpl(InventoryItemRepository profileRepository) {
        this.inventoryItemRepository = profileRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean handle(ExistsBySkuIdentifierQuery query) {
        return inventoryItemRepository.existsBySkuIdentifier(
                new SkuIdentifier(query.skuIdentifier())
        );
    }
}


