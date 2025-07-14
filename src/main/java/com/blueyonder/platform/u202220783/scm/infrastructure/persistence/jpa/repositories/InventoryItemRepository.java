package com.blueyonder.platform.u202220783.scm.infrastructure.persistence.jpa.repositories;

import com.blueyonder.platform.u202220783.scm.domain.model.aggregates.InventoryItem;
import com.blueyonder.platform.u202220783.shared.domain.model.valueobjects.SkuIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * InventoryItem Repository
 */
@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {
    /**
     * Find an InventoryItem by SkuIdentifier
     *
     * @param skuIdentifier the unique sku identifier
     * @return A {@link InventoryItem} instance if the sku identifier is valid, otherwise empty
     */
    Optional<InventoryItem> findBySkuIdentifier(SkuIdentifier skuIdentifier);

    /**
     * Check if an InventoryItem exists by SkuIdentifier
     *
     * @param skuIdentifier the unique sku identifier
     * @return True if the sku identifier exists, otherwise false
     */
    boolean existsBySkuIdentifier(SkuIdentifier skuIdentifier);
}


