package com.blueyonder.platform.u202220783.sdm.infrastructure.persistence.jpa.repositories;

import com.blueyonder.platform.u202220783.shared.domain.model.valueobjects.SkuIdentifier;
import com.blueyonder.platform.u202220783.sdm.domain.model.aggregates.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * OrderItem Repository
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    /**
     * Check if an OrderItem exists by order Id and Sku Identifier
     *
     * @param orderId The order id which this item belongs to
     * @param skuIdentifier the unique sku identifier of the inventory item
     * @return True if the email address exists, otherwise false
     */
    boolean existsByOrderIdAndSkuIdentifier(Long orderId, SkuIdentifier skuIdentifier);
}


