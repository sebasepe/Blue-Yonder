package com.blueyonder.platform.u202220783.scm.domain.services;

import com.blueyonder.platform.u202220783.scm.domain.model.aggregates.InventoryItem;
import com.blueyonder.platform.u202220783.scm.domain.model.queries.ExistsBySkuIdentifierQuery;

import java.util.List;
import java.util.Optional;

/**
 * InventoryItem Query Service
 */
public interface InventoryItemQueryService {
    /**
     * Handle Exists By SkuIdentifier Query
     *
     * @param query The {@link ExistsBySkuIdentifierQuery} Query
     * @return true if exists, false otherwise
     */
    boolean handle(ExistsBySkuIdentifierQuery query);
}


