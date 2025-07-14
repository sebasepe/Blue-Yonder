package com.blueyonder.platform.u202220783.scm.domain.model.entities;

import com.blueyonder.platform.u202220783.scm.domain.model.valueobjects.InventoryItemStatuses;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;

/**
 * InventoryItemStatus entity
 * <p>
 *     This entity represents the inventoryItemStatus of a user in the system.
 *     It is used to define the permissions of a user.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
public class InventoryItemStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private InventoryItemStatuses name;

    public InventoryItemStatus(InventoryItemStatuses name) {
        this.name = name;
    }

    /**
     * Get the name of the inventoryItemStatus as a string
     * @return the name of the inventoryItemStatus as a string
     */
    public String getStringName() {
        return name.name();
    }

    /**
     * Get the default inventoryItemStatus
     * @return the default inventoryItemStatus
     */
    public static InventoryItemStatus getDefaultInventoryItemStatus() {
        return new InventoryItemStatus(InventoryItemStatuses.WITH_STOCK);
    }

    /**
     * Get the inventoryItemStatus from its name
     * @param name the name of the inventoryItemStatus
     * @return the inventoryItemStatus
     */
    public static InventoryItemStatus toInventoryItemStatusFromName(String name) {
        return new InventoryItemStatus(InventoryItemStatuses.valueOf(name));
    }

    /**
     * Validate the inventoryItemStatus set
     * <p>
     *     This method validates the inventoryItemStatus set and returns the default inventoryItemStatus if the set is empty.
     * </p>
     * @param inventoryItemStatuses the inventoryItemStatus set
     * @return the inventoryItemStatus set
     */
    public static List<InventoryItemStatus> validateInventoryItemStatusSet(List<InventoryItemStatus> inventoryItemStatuses) {
        if (inventoryItemStatuses == null || inventoryItemStatuses.isEmpty()) {
            return List.of(getDefaultInventoryItemStatus());
        }
        return inventoryItemStatuses;
    }

}


