package com.blueyonder.platform.u202220783.scm.infrastructure.persistence.jpa.repositories;

import com.blueyonder.platform.u202220783.scm.domain.model.entities.InventoryItemStatus;
import com.blueyonder.platform.u202220783.scm.domain.model.valueobjects.InventoryItemStatuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface is responsible for providing the InventoryItemStatus entity related operations.
 * It extends the JpaRepository interface.
 */
@Repository
public interface InventoryItemStatusRepository extends JpaRepository<InventoryItemStatus, Long> {

    /**
     * This method is responsible for finding the inventoryItemStatus by name.
     * @param name The inventoryItemStatus name.
     * @return The inventoryItemStatus object.
     */
    Optional<InventoryItemStatus> findByName(InventoryItemStatuses name);

    /**
     * This method is responsible for checking if the inventoryItemStatus exists by name.
     * @param name The inventoryItemStatus name.
     * @return True if the inventoryItemStatus exists, false otherwise.
     */
    boolean existsByName(InventoryItemStatuses name);

}


